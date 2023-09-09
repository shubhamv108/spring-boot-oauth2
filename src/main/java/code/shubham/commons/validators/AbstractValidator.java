package code.shubham.commons.validators;

public abstract class AbstractValidator<OBJECT> implements code.shubham.commons.validators.IValidator<OBJECT> {

	private final java.util.Collection<java.util.Map<String, java.util.Collection<String>>> errorMessagesList = new java.util.ArrayList<>();

	private java.util.Map<String, java.util.Collection<String>> messages = new java.util.LinkedHashMap<>();

	public void next() {
		this.errorMessagesList.add(messages);
		this.messages = new java.util.LinkedHashMap<>();
	}

	@SuppressWarnings("all")
	protected boolean putMessage(final String messageKey, final String messageValueFormat) {
		return this.putMessage(messageKey, messageValueFormat, null);
	}

	protected boolean putMessage(final String messageKey, final String messageValueFormat,
			final Object... messageValueArguments) {
		String messageValue = String.format(messageValueFormat, messageValueArguments);
		java.util.Collection<String> messagesValues = this.messages.get(messageKey);
		if (messagesValues == null)
			this.messages.put(messageKey, messagesValues = new java.util.ArrayList<>());
		return messagesValues.add(messageValue);
	}

	protected boolean putMessages(final String messageKey, final java.util.Collection<String> messageValue) {
		java.util.Collection<String> messagesValues = this.messages.get(messageKey);
		if (messagesValues == null)
			this.messages.put(messageKey, messagesValues = new java.util.ArrayList<>());
		return messagesValues.addAll(messageValue);
	}

	@Override
	public boolean hasMessages() {
		return !this.getMessages().isEmpty();
	}

	@Override
	public java.util.Map<String, java.util.Collection<String>> getResult() {
		java.util.Map<String, java.util.Collection<String>> copy = new java.util.LinkedHashMap<>();
		this.messages.forEach((k, v) -> copy.put(k, new java.util.ArrayList<String>(v)));
		return copy;
	}

	private java.util.Map<String, java.util.Collection<String>> getMessages() {
		return this.messages;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("[\n");
		errorMessagesList.stream().map(errorMessagesMap -> {
			StringBuilder mapBBuilder = new StringBuilder("{\n");
			errorMessagesMap.forEach((k, v) -> {
				mapBBuilder.append("\t").append('"').append(k).append('"').append(": ").append("[\n");
				v.forEach(e -> mapBBuilder.append("\t\t").append('"').append(e).append('"').append(",\n"));
				mapBBuilder.replace(mapBBuilder.lastIndexOf(","), mapBBuilder.lastIndexOf(",") + 1, "");
				mapBBuilder.append('\t').append("]\n");
				if ("]".equals(mapBBuilder.charAt(mapBBuilder.length() - 1))) {
					mapBBuilder.append(',');
				}
				mapBBuilder.append('\n');
			});
			mapBBuilder.append("}");
			return mapBBuilder;
		}).forEach(builder::append);
		builder.append("]\n");
		return builder.toString();
	}

}
