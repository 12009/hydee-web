package com.hydee.hydee.service.impl;

import com.hydee.hdsec.common.constant.ErrorCodes;
import com.hydee.hdsec.common.exception.ServiceException;
import com.hydee.hdsec.dao.CompanyTrainBaseDao;
import com.hydee.hdsec.dao.CompanyTrainExercisesDao;
import com.hydee.hdsec.dao.CompanyTrainExercisesTypeDao;
import com.hydee.hdsec.entity.*;
import com.hydee.hydee.service.CompanyTrainExercisesService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.StringUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by King.Liu
 * 2016/8/25.
 */
@Service
public class CompanyTrainExercisesServiceImpl implements CompanyTrainExercisesService {
    private static final String[] CHOOSE = {"A","B","C","D","E","F"};
    private static final String SINGLE_CHOOSE = "AB";
    @Resource(name = "companyTrainExercisesDao")
    CompanyTrainExercisesDao companyTrainExercisesDao;
    @Autowired
    CompanyTrainBaseDao companyTrainBaseDao;
    @Autowired
    CompanyTrainExercisesTypeDao companyTrainExercisesTypeDao;
    @Override
    public List<CompanyTrainExercises> selectByClassId(Long classId) throws Exception {
        return companyTrainExercisesDao.selectByClassId(classId);
    }

    @Override
    public List<CompanyTrainExercises> selectByBaseId(Long baseId) throws Exception {
        return companyTrainExercisesDao.selectByBaseId(baseId);
    }

    @Override
    public List<CompanyTrainExercises> selectAllListPage(CompanyTrainExercises trainExercises) throws Exception {
        return companyTrainExercisesDao.selectAllListPage(trainExercises);
    }

    @Override
    public List<CompanyTrainExercises> selectRandomExercises(Map<String, Object> map) throws Exception {
        return companyTrainExercisesDao.selectRandomExercises(map);
    }

    @Override
    public List<CompanyTrainExercisesOptions> selectOptionsByExercisesId(Long exercisesId) throws Exception {
        return companyTrainExercisesDao.selectOptionsByExercisesId(exercisesId);
    }

    @Override
    public int insertErrorOption(List<CompanyTrainErrorOption> trainErrorOption) throws Exception {
        return companyTrainExercisesDao.insertErrorOption(trainErrorOption);
    }

    @Override
    public List<CompanyTrainErrorOption> selectErrorOption(CompanyTrainErrorOption trainErrorOption) throws Exception {
        return companyTrainExercisesDao.selectErrorOption(trainErrorOption);
    }

    @Override
    public CompanyTrainExercises selectByExercisesId(Long exercisesId) throws Exception {
        return companyTrainExercisesDao.selectByExercisesId(exercisesId);
    }

    @Override
    public List<CompanyTrainExercises> selectHistoryExercises(CompanyTrainErrorOption trainErrorOption) throws Exception {
        return companyTrainExercisesDao.selectHistoryExercises(trainErrorOption);
    }

    @Override
    public int deleteExercises(Long exercisesId) throws Exception {
        int count = companyTrainBaseDao.selectBaseExercisesByExercisesId(exercisesId);
        if(count > 0){
            throw new ServiceException(ErrorCodes.BASE_EXERCISE_NOW);
        }
        CompanyTrainExercises companyTrainExercises = new CompanyTrainExercises();
        companyTrainExercises.setIsDelete(1);
        companyTrainExercises.setExercisesId(exercisesId);
        companyTrainExercisesDao.updateByExercises(companyTrainExercises);//逻辑删除
//        companyTrainExercisesDao.deleteExercises(exercisesId);
        return 0;
    }

    @Override
    public String deleteExercisesAll(List<Long> exercisesId) throws Exception {
        StringBuffer sb = new StringBuffer();
        for (Long exercises : exercisesId) {
            int count = companyTrainBaseDao.selectBaseExercisesByExercisesId(exercises);
            if(count > 0){
                sb.append(exercises).append(",");
            }else{
//                companyTrainExercisesDao.deleteExercisesOptionsByExercisesId(exercises);
//                companyTrainExercisesDao.deleteExercises(exercises);
                CompanyTrainExercises companyTrainExercises = new CompanyTrainExercises();
                companyTrainExercises.setIsDelete(1);
                companyTrainExercises.setExercisesId(exercises);
                companyTrainExercisesDao.updateByExercises(companyTrainExercises);//逻辑删除
            }
        }
        return sb.toString().equals("") ? "" : sb.deleteCharAt(sb.length()-1).toString();
    }

    @Override
    public Map<String, Object> exportExercises(MultipartFile attach,CompanyUser user) throws Exception {
        String fileName = attach.getOriginalFilename();
        InputStream inputStream = attach.getInputStream();
        Map<String,Object> map = new HashMap<String,Object>();
        Workbook wb = null;
        if(fileName.endsWith("xls")){
            wb = new HSSFWorkbook(inputStream);//解析xls格式
        }else if(fileName.endsWith("xlsx")){
            wb = new XSSFWorkbook(inputStream);//解析xlsx格式
        }
        Sheet sheet = wb.getSheetAt(0);//第一个工作表
        int firstRowIndex = sheet.getFirstRowNum();
        int lastRowIndex = sheet.getLastRowNum();
        if(lastRowIndex > 500){
            throw new ServiceException(ErrorCodes.EXCL_COUNT_SURPASS);
        }
        JSONArray jArray = new JSONArray();
        StringBuffer errorSb = new StringBuffer();
        //读取Excel表
        for(int rIndex = firstRowIndex+1; rIndex <= lastRowIndex; rIndex ++){
            Row _row = sheet.getRow(rIndex);
            Cell _cell = _row.getCell(0);
            if(_cell == null || "".equals(_cell.toString())){
                errorSb.append(rIndex).append(",");
                continue;
            }
            String _classTypeName = _cell == null ? "" : _cell.toString();
            _classTypeName = _classTypeName.trim();
            if("".equals(_classTypeName)){
                errorSb.append(rIndex).append(",");
                continue;
            }
            CompanyTrainExercisesType trainExercisesType = new CompanyTrainExercisesType();
            trainExercisesType.setName(_classTypeName);
            trainExercisesType.setCustomerId(user.getCustomerId());
            CompanyTrainExercisesType exercisesType = companyTrainExercisesTypeDao.selectTypeByName(trainExercisesType);
            Long _classTypeId = 0L;
            if(null == exercisesType){
                errorSb.append(rIndex).append(",");
                continue;
            }else{
                _classTypeId = exercisesType.getClassTypeId();
            }
            _cell = _row.getCell(1);

            String _type = _cell == null ? "" : _cell.toString();
            _type.replace(" ","");
            if("".equals(_type)){
                errorSb.append(rIndex).append(",");
                continue;
            }
            String _exercisesType = "";
            if(_type.indexOf("单选题") != -1){
                _exercisesType = "singleTab";//单选
            }else if(_type.indexOf("多选题") != -1){
                _exercisesType = "multiTab";//多选
            }else if(_type.indexOf("判断题") != -1){
                _exercisesType = "judgeTab";//多选
            }else{
                errorSb.append(rIndex).append(",");
                continue;
            }
            _cell = _row.getCell(2);
            String _content = _cell == null ? "" : _cell.toString();
            if("".equals(_content)){
                errorSb.append(rIndex).append(",");
                continue;
            }
            _cell = _row.getCell(3);
            String _optionA = _cell == null ? "" : _cell.toString();
            _cell = _row.getCell(4);
            String _optionB = _cell == null ? "" : _cell.toString();
            _cell = _row.getCell(5);
            String _optionC = _cell == null ? "" : _cell.toString();
            _cell = _row.getCell(6);
            String _optionD = _cell == null ? "" : _cell.toString();
            _cell = _row.getCell(7);
            String _optionE = _cell == null ? "" : _cell.toString();
            _cell = _row.getCell(8);
            String _optionF = _cell == null ? "" : _cell.toString();
            _cell = _row.getCell(9);
            String[] _answers = _cell == null ? new String[]{} : _cell.toString().replaceAll("[\\s\\n\\r]+", "").toUpperCase().split("");
            if(_answers.length <= 0){
                errorSb.append(rIndex).append(",");
                continue;
            }
            if(_exercisesType.equals("judgeTab") || _exercisesType.equals("singleTab")){//单选和判断题只能有一个答案
                if(_answers.length > 2){
                    errorSb.append(rIndex).append(",");
                    continue;
                }
            }else{//多选题必须为多选
                if(_answers.length < 3){
                    errorSb.append(rIndex).append(",");
                    continue;
                }
            }
            boolean chooseFlag = false;
            int action = 3;
            if(_exercisesType.equals("judgeTab")){//判断题，AB选项必须选择，答案也只能选择AB
                for(int j=0;j<_answers.length;j++){
                    if(SINGLE_CHOOSE.indexOf(_answers[j]) == -1){
                        chooseFlag = true;
                        break;
                    }
                }
                if("".equals(_optionA)){
                    errorSb.append(rIndex).append(",");
                    continue;
                }
                if("".equals(_optionB)){
                    errorSb.append(rIndex).append(",");
                    continue;
                }
            }else{
                for(int j=0;j<_answers.length;j++){
                    String _str = _answers[j];
                    for(int k = action ; k <= action+5 ;k++){
                        if(_str.equals(CHOOSE[k-action])){
                            if(_row.getCell(k).toString().equals("")){
                                chooseFlag = true;
                                break;
                            }
                        }
                    }
                }
            }
            if(chooseFlag){
                errorSb.append(rIndex).append(",");
                continue;
            }
            JSONObject jObj = new JSONObject();
            jObj.put("classTypeId", _classTypeId);
            jObj.put("type", _exercisesType);
            jObj.put("content", _content);
            jObj.put("optionA", _optionA);
            jObj.put("optionB", _optionB);
            jObj.put("optionC", _optionC);
            jObj.put("optionD", _optionD);
            jObj.put("optionE", _optionE);
            jObj.put("optionF", _optionF);
            jObj.put("answer", StringUtil.join(_answers,""));
            jArray.add(jObj);
        }
        map.put("trueArray",jArray);
        map.put("error",errorSb.toString().equals("") ? "" : errorSb.deleteCharAt(errorSb.length()-1).toString());
        return map;
    }

    @Override
    public int saveExercisesExcel(JSONArray jsonArray, CompanyUser user) throws Exception {
        for (int i = 0; i < jsonArray.size(); i++) {
            Long exercisesId = 0L;
            JSONObject jsonObj = (JSONObject) jsonArray.get(i);
            CompanyTrainExercises trainExercises = new CompanyTrainExercises();
            String _answer = jsonObj.get("answer").toString();
            trainExercises.setAnswer(_answer);
            trainExercises.setType(jsonObj.get("type").toString());
            trainExercises.setContent(jsonObj.get("content").toString());
            trainExercises.setClassTypeId(Long.parseLong(jsonObj.get("classTypeId").toString()));
            trainExercises.setCustomerId(user.getCustomerId());
            trainExercises.setCreateId(user.getId());
            companyTrainExercisesDao.insertExercises(trainExercises);
            exercisesId = trainExercises.getExercisesId();
            for (int j = 0; j < CHOOSE.length; j++) {
                CompanyTrainExercisesOptions option = new CompanyTrainExercisesOptions();
                String content = jsonObj.get("option"+CHOOSE[j]).toString();
                if(content.equals("")){
                    break;
                }
                String optionNo = CHOOSE[j];
                option.setContent(content);
                option.setOptionNo(optionNo);
                String answer = trainExercises.getAnswer();
                int isAnswer = 0;
                char[] chs = answer.toCharArray();
                for(Character ch:chs){
                    if(optionNo.equals(ch.toString())){
                        isAnswer = 1;
                        break;
                    }
                }
                option.setIsAnswer(isAnswer);
                option.setExercisesId(exercisesId);
                companyTrainExercisesDao.insertExercisesOptions(option);
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        for (int i = 0; i < CHOOSE.length; i++) {
            System.out.println(CHOOSE[i].toUpperCase());
        }
    }
}
