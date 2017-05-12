package org.clock.bs.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by clock on 2017/4/26.
 */
@Entity
@Table(name = "bs_consumeform", schema = "clock_bs", catalog = "")
public class BsConsumeformBo  implements Serializable {
    private int consumeFormId;
    private String consumeFormNumber;
    private int projectId;
    private Date startTime;
    private Date endTime;
    private Integer time;
    private String consumeStandard;
    private String accommodationStandard;
    private String consumePurpose;
    private String myFoodData;
    private Integer allFoodConsume;
    private String myAccommodationData;
    private Integer allAccommodationConsume;
    private String myTravelData;
    private Integer allTravelConsume;
    private Integer allConsume;
    private Integer flag;
    private String message;
    private Integer staffId;

    @Id
    @Column(name = "consumeFormId")
    public int getConsumeFormId() {
        return consumeFormId;
    }

    public void setConsumeFormId(int consumeFormId) {
        this.consumeFormId = consumeFormId;
    }

    @Basic
    @Column(name = "consumeFormNumber")
    public String getConsumeFormNumber() {
        return consumeFormNumber;
    }

    public void setConsumeFormNumber(String consumeFormNumber) {
        this.consumeFormNumber = consumeFormNumber;
    }

    @Basic
    @Column(name = "projectId")
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "startTime")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "endTime")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "time")
    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    @Basic
    @Column(name = "consumeStandard")
    public String getConsumeStandard() {
        return consumeStandard;
    }

    public void setConsumeStandard(String consumeStandard) {
        this.consumeStandard = consumeStandard;
    }

    @Basic
    @Column(name = "accommodationStandard")
    public String getAccommodationStandard() {
        return accommodationStandard;
    }

    public void setAccommodationStandard(String accommodationStandard) {
        this.accommodationStandard = accommodationStandard;
    }

    @Basic
    @Column(name = "consumePurpose")
    public String getConsumePurpose() {
        return consumePurpose;
    }

    public void setConsumePurpose(String consumePurpose) {
        this.consumePurpose = consumePurpose;
    }

    @Basic
    @Column(name = "myFoodData")
    public String getMyFoodData() {
        return myFoodData;
    }

    public void setMyFoodData(String myFoodData) {
        this.myFoodData = myFoodData;
    }

    @Basic
    @Column(name = "allFoodConsume")
    public Integer getAllFoodConsume() {
        return allFoodConsume;
    }

    public void setAllFoodConsume(Integer allFoodConsume) {
        this.allFoodConsume = allFoodConsume;
    }

    @Basic
    @Column(name = "myAccommodationData")
    public String getMyAccommodationData() {
        return myAccommodationData;
    }

    public void setMyAccommodationData(String myAccommodationData) {
        this.myAccommodationData = myAccommodationData;
    }

    @Basic
    @Column(name = "allAccommodationConsume")
    public Integer getAllAccommodationConsume() {
        return allAccommodationConsume;
    }

    public void setAllAccommodationConsume(Integer allAccommodationConsume) {
        this.allAccommodationConsume = allAccommodationConsume;
    }

    @Basic
    @Column(name = "myTravelData")
    public String getMyTravelData() {
        return myTravelData;
    }

    public void setMyTravelData(String myTravelData) {
        this.myTravelData = myTravelData;
    }

    @Basic
    @Column(name = "allTravelConsume")
    public Integer getAllTravelConsume() {
        return allTravelConsume;
    }

    public void setAllTravelConsume(Integer allTravelConsume) {
        this.allTravelConsume = allTravelConsume;
    }

    @Basic
    @Column(name = "allConsume")
    public Integer getAllConsume() {
        return allConsume;
    }

    public void setAllConsume(Integer allConsume) {
        this.allConsume = allConsume;
    }

    @Basic
    @Column(name = "flag")
    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    @Basic
    @Column(name = "message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BsConsumeformBo that = (BsConsumeformBo) o;

        if (consumeFormId != that.consumeFormId) return false;
        if (projectId != that.projectId) return false;
        if (consumeFormNumber != null ? !consumeFormNumber.equals(that.consumeFormNumber) : that.consumeFormNumber != null)
            return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (consumeStandard != null ? !consumeStandard.equals(that.consumeStandard) : that.consumeStandard != null)
            return false;
        if (accommodationStandard != null ? !accommodationStandard.equals(that.accommodationStandard) : that.accommodationStandard != null)
            return false;
        if (consumePurpose != null ? !consumePurpose.equals(that.consumePurpose) : that.consumePurpose != null)
            return false;
        if (myFoodData != null ? !myFoodData.equals(that.myFoodData) : that.myFoodData != null) return false;
        if (allFoodConsume != null ? !allFoodConsume.equals(that.allFoodConsume) : that.allFoodConsume != null)
            return false;
        if (myAccommodationData != null ? !myAccommodationData.equals(that.myAccommodationData) : that.myAccommodationData != null)
            return false;
        if (allAccommodationConsume != null ? !allAccommodationConsume.equals(that.allAccommodationConsume) : that.allAccommodationConsume != null)
            return false;
        if (myTravelData != null ? !myTravelData.equals(that.myTravelData) : that.myTravelData != null) return false;
        if (allTravelConsume != null ? !allTravelConsume.equals(that.allTravelConsume) : that.allTravelConsume != null)
            return false;
        if (allConsume != null ? !allConsume.equals(that.allConsume) : that.allConsume != null) return false;
        if (flag != null ? !flag.equals(that.flag) : that.flag != null) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = consumeFormId;
        result = 31 * result + (consumeFormNumber != null ? consumeFormNumber.hashCode() : 0);
        result = 31 * result + projectId;
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (consumeStandard != null ? consumeStandard.hashCode() : 0);
        result = 31 * result + (accommodationStandard != null ? accommodationStandard.hashCode() : 0);
        result = 31 * result + (consumePurpose != null ? consumePurpose.hashCode() : 0);
        result = 31 * result + (myFoodData != null ? myFoodData.hashCode() : 0);
        result = 31 * result + (allFoodConsume != null ? allFoodConsume.hashCode() : 0);
        result = 31 * result + (myAccommodationData != null ? myAccommodationData.hashCode() : 0);
        result = 31 * result + (allAccommodationConsume != null ? allAccommodationConsume.hashCode() : 0);
        result = 31 * result + (myTravelData != null ? myTravelData.hashCode() : 0);
        result = 31 * result + (allTravelConsume != null ? allTravelConsume.hashCode() : 0);
        result = 31 * result + (allConsume != null ? allConsume.hashCode() : 0);
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "staffId")
    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }
}
