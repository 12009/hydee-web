package com.hydee.hydee.service;

import com.hydee.hdsec.entity.CompanyTrainErrorOption;
import com.hydee.hdsec.entity.CompanyTrainExercises;
import com.hydee.hdsec.entity.CompanyTrainExercisesOptions;
import com.hydee.hdsec.entity.CompanyUser;
import net.sf.json.JSONArray;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by King.Liu
 * 2016/8/25.
 */
public interface CompanyTrainExercisesService {

    public List<CompanyTrainExercises> selectByClassId(Long classId) throws Exception;

    public List<CompanyTrainExercises> selectByBaseId(Long baseId) throws Exception;

    public List<CompanyTrainExercises> selectAllListPage(CompanyTrainExercises trainExercises) throws Exception;

    public List<CompanyTrainExercises> selectRandomExercises(Map<String,Object> map) throws Exception;

    public List<CompanyTrainExercisesOptions> selectOptionsByExercisesId(Long exercisesId) throws Exception;

    public int insertErrorOption(List<CompanyTrainErrorOption> trainErrorOptions) throws Exception;

    public List<CompanyTrainErrorOption> selectErrorOption(CompanyTrainErrorOption trainErrorOption) throws Exception;

    public CompanyTrainExercises selectByExercisesId(Long exercisesId) throws Exception;

    public List<CompanyTrainExercises> selectHistoryExercises(CompanyTrainErrorOption trainErrorOption) throws Exception;

    public int deleteExercises(Long exercisesId) throws Exception;

    public Map<String,Object> exportExercises(MultipartFile attach,CompanyUser user) throws Exception;

    public int saveExercisesExcel(JSONArray jsonArray, CompanyUser user) throws Exception;

    public String deleteExercisesAll(List<Long> exercisesId) throws Exception;
}
