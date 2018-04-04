package config;

public class Practice {
	private String practiceName;
	private String input;

	/**
	 * @return practiceName
	 */
	public String getPracticeName() {
		return practiceName;
	}

	/**
	 * @param practiceName セットする practiceName
	 */
	public void setPracticeName(String practiceName) {
		this.practiceName = practiceName;
	}

	/**
	 * @return input
	 */
	public String getInput() {
		return input;
	}

	/**
	 * @param input セットする input
	 */
	public void setInput(String input) {
		this.input = input;
	}

	/* (非 Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Practice [practiceName=");
		builder.append(practiceName);
		builder.append(", input=");
		builder.append(input);
		builder.append("]");
		return builder.toString();
	}

}
