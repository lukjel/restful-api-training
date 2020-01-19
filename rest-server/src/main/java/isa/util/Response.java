package isa.util;

import com.sun.istack.Nullable;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class Response {

	public static JsonObject appendOk(JsonObject o) {
		JsonObjectBuilder builder = Json.createObjectBuilder(o);
		builder.add("result", "ok");
		return builder.build();
	}

	public static JsonObject ok() {
		JsonObjectBuilder builder = Json.createObjectBuilder();
		builder.add("result", "ok");
		return builder.build();
	}

	public static JsonObject ok(String name, Object value, Object... other) {
		return appendOk(JsonHelper.toJson(name, value, other));
	}

	public static JsonObject fail(@Nullable String s) {
		return JsonHelper.toJson(
			"result", "fail",
			"message", s
		);
	}
}
