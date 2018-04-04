package config;

import java.util.List;

public class Config {
	private String path;
	private String outputPath;
	private List<Practice> practiceList;

	/**
	 * @return path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path セットする path
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return outputPath
	 */
	public String getOutputPath() {
		return outputPath;
	}

	/**
	 * @param outputPath セットする outputPath
	 */
	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}

	/**
	 * @return practiceList
	 */
	public List<Practice> getPracticeList() {
		return practiceList;
	}

	/**
	 * @param practiceList セットする practiceList
	 */
	public void setPracticeList(List<Practice> practiceList) {
		this.practiceList = practiceList;
	}

	/* (非 Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Config [path=");
		builder.append(path);
		builder.append(", practiceList=");
		builder.append(practiceList);
		builder.append("]");
		return builder.toString();
	}
}
