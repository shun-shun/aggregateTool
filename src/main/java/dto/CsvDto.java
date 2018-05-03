package dto;

public class CsvDto {

    private String employeeName;

    private String jarName;

    private String argument;

    private String result;

    /**
     * @return employeeName
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * @param employeeName セットする employeeName
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    /**
     * @return jarName
     */
    public String getJarName() {
        return jarName;
    }

    /**
     * @param jarName セットする jarName
     */
    public void setJarName(String jarName) {
        this.jarName = jarName;
    }

    /**
     * @return argument
     */
    public String getArgument() {
        return argument;
    }

    /**
     * @param argument セットする argument
     */
    public void setArgument(String argument) {
        this.argument = argument;
    }

    /**
     * @return result
     */
    public String getResult() {
        return result;
    }

    /**
     * @param result セットする result
     */
    public void setResult(String result) {
        this.result = result;
    }

    /*
     * (非 Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CsvDto [employeeName=");
        builder.append(employeeName);
        builder.append(", jarName=");
        builder.append(jarName);
        builder.append(", argument=");
        builder.append(argument);
        builder.append(", result=");
        builder.append(result);
        builder.append("]");
        return builder.toString();
    }


}
