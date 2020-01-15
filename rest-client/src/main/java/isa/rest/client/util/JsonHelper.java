package isa.rest.client.util;

import javax.json.*;
import java.io.StringReader;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collection;

public class JsonHelper {

	public static JsonObject appendOk(JsonObject o) {
		JsonObjectBuilder builder = Json.createObjectBuilder(o);
		builder.add("result", "ok");
		return builder.build();
	}

	public static JsonArray toArray(Collection list) {
		return toArray(list.toArray());
	}

	public static JsonArray toArray(Object... other) {
		JsonArrayBuilder builder = Json.createArrayBuilder();
		for (Object value : other) {
			if (value instanceof BigDecimal) {
				builder.add(((BigDecimal) value).stripTrailingZeros().toPlainString());
			} else if (value instanceof String) {
				builder.add((String) value);
			} else if (value instanceof Integer) {
				builder.add((Integer) value);
			} else if (value instanceof Long) {
				builder.add((Long) value);
			} else if (value instanceof Instant) {
				builder.add(((Instant) value).toString());
			} else if (value instanceof Boolean) {
				builder.add((Boolean) value);
			} else if (value instanceof JsonArrayBuilder) {
				builder.add((JsonArrayBuilder) value);
			} else if (value instanceof JsonObjectBuilder) {
				builder.add((JsonObjectBuilder) value);
			} else if (value instanceof JsonValue) {
				builder.add((JsonValue) value);
			}
		}
		return builder.build();
	}

	public static JsonObject toJson(String name, Object value, Object... other) {
		JsonObjectBuilder builder = Json.createObjectBuilder();

		if (value != null) {
			if (value instanceof BigDecimal) {
				builder.add(name, ((BigDecimal) value).stripTrailingZeros().toPlainString());
			} else if (value instanceof String) {
				builder.add(name, (String) value);
			} else if (value instanceof Integer) {
				builder.add(name, (Integer) value);
			} else if (value instanceof Long) {
				builder.add(name, (Long) value);
			} else if (value instanceof Instant) {
				builder.add(name, ((Instant) value).toString());
			} else if (value instanceof Boolean) {
				builder.add(name, (Boolean) value);
			} else if (value instanceof JsonArrayBuilder) {
				builder.add(name, (JsonArrayBuilder) value);
			} else if (value instanceof JsonObjectBuilder) {
				builder.add(name, (JsonObjectBuilder) value);
			} else if (value instanceof JsonValue) {
				builder.add(name, (JsonValue) value);
			}
		}

		if (other != null) {
			if (other.length % 2 != 0) {
				throw new IllegalArgumentException("wrong number of elements");
			}

			for (int i = 0; i < other.length; i += 2) {
				Object object = other[i + 1];
				if (object instanceof BigDecimal) {
					builder.add((String) other[i], ((BigDecimal) object).stripTrailingZeros().toPlainString());
				} else if (object instanceof String) {
					builder.add((String) other[i], (String) object);
				} else if (object instanceof Integer) {
					builder.add((String) other[i], (Integer) object);
				} else if (object instanceof Long) {
					builder.add((String) other[i], (Long) object);
				} else if (object instanceof Instant) {
					builder.add((String) other[i], ((Instant) object).toString());
				} else if (object instanceof Boolean) {
					builder.add((String) other[i], (Boolean) object);
				} else if (object instanceof JsonArrayBuilder) {
					builder.add((String) other[i], (JsonArrayBuilder) object);
				} else if (object instanceof JsonObjectBuilder) {
					builder.add((String) other[i], (JsonObjectBuilder) object);
				} else if (object instanceof JsonValue) {
					builder.add((String) other[i], (JsonValue) object);
				}
			}
		}
		return builder.build();
	}

	public static JsonObject ok() {
		JsonObjectBuilder builder = Json.createObjectBuilder();
		builder.add("result", "ok");
		return builder.build();
	}

	public static JsonObject ok(String name, Object value, Object... other) {
		return appendOk(toJson(name, value, other));
	}

	public static JsonObject convert(String str) {
		StringReader reader = new StringReader(str);
		try (JsonReader jsonReader = Json.createReader(reader)) {
			return jsonReader.readObject();
		}
	}
}
