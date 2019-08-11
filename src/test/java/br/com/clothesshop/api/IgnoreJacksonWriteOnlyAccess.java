package br.com.clothesshop.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

public class IgnoreJacksonWriteOnlyAccess extends JacksonAnnotationIntrospector {

	private static final long serialVersionUID = -3167046103552108769L;

	@Override
	public JsonProperty.Access findPropertyAccess(Annotated m) {
		JsonProperty.Access access = super.findPropertyAccess(m);
		if (access == JsonProperty.Access.WRITE_ONLY) {
			return JsonProperty.Access.AUTO;
		}
		return access;
	}

}
