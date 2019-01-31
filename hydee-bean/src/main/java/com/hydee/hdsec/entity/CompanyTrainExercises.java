package com.hydee.hdsec.entity;

import com.hydee.hdsec.entity.utils.DateUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 培训习题实体
 */
public class CompanyTrainExercises extends Page {
    private static final long serialVersionUID = 1L;
    private Long exercisesId;

    private String content;//内容

    private String type;//类型(singleTab:单选，multiTab：多选，judgeTab：判断题)

    private String answer;//答案

    private Long createId;//创建人

    private Date createTime;//创建时间

    private Long modifiedId;//修改人

    private Date modifiedTime;//修改时间

    private Long classTypeId;//习题类别

    private String customerId;//所属连锁

    private String realName;//创建人

    private String name;//类别名称

    private String chooseNo;//选择选项

    private Integer isDelete;//0：正常、1：已经删除

    private List<Option> options;

    private List<CompanyTrainExercisesOptions> optionsList;

    private String optionA;

    private String optionB;

    private String optionC;

    private String optionD;

    public Long getExercisesId() {
        return exercisesId;
    }

    public void setExercisesId(Long exercisesId) {
        this.exercisesId = exercisesId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateTimeFmt() {
        return DateUtil.format(createTime).replace(" ","</br>");
    }

    public Long getModifiedId() {
        return modifiedId;
    }

    public void setModifiedId(Long modifiedId) {
        this.modifiedId = modifiedId;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public List<CompanyTrainExercisesOptions> getOptionsList() {
        return optionsList;
    }

    public void setOptionsList(List<CompanyTrainExercisesOptions> optionsList) {
        this.optionsList = optionsList;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public Long getClassTypeId() {
        return classTypeId;
    }

    public void setClassTypeId(Long classTypeId) {
        this.classTypeId = classTypeId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChooseNo() {
        return chooseNo;
    }

    public void setChooseNo(String chooseNo) {
        this.chooseNo = chooseNo;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}