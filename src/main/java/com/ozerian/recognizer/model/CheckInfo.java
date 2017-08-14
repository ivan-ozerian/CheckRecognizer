package com.ozerian.recognizer.model;

public class CheckInfo {

    private Integer checkId;
    private String fileName;
    private Double maxCheckSum;

    public CheckInfo(Integer checkId, String fileName, Double maxCheckSum) {
        this.checkId = checkId;
        this.fileName = fileName;
        this.maxCheckSum = maxCheckSum;
    }

    public Integer getCheckId() {
        return checkId;
    }

    public String getFileName() {
        return fileName;
    }

    public Double getMaxCheckSum() {
        return maxCheckSum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CheckInfo)) return false;

        CheckInfo checkInfo = (CheckInfo) o;

        if (checkId != null ? !checkId.equals(checkInfo.checkId) : checkInfo.checkId != null) return false;
        if (fileName != null ? !fileName.equals(checkInfo.fileName) : checkInfo.fileName != null) return false;
        return maxCheckSum != null ? maxCheckSum.equals(checkInfo.maxCheckSum) : checkInfo.maxCheckSum == null;

    }

    @Override
    public int hashCode() {
        int result = checkId != null ? checkId.hashCode() : 0;
        result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
        result = 31 * result + (maxCheckSum != null ? maxCheckSum.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CheckInfo{" +
                "checkId=" + checkId +
                ", fileName='" + fileName + '\'' +
                ", maxCheckSum=" + maxCheckSum +
                '}';
    }
}
