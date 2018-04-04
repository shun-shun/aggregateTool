package converter;

import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverter {

	   private static ObjectMapper mapper = new ObjectMapper();

	   private JsonConverter() {
	      // do nothing.
	   }

	   public static String toString(final Object object) throws JsonGenerationException, JsonMappingException,
	         IOException {

	      String json = mapper.writeValueAsString(object);

	      return json;
	   }

	   public static <T> T toObject(final File jsonFile, final Class<T> clazz) throws JsonParseException,
	         JsonMappingException, IOException {

	      T object = null;

	      if (jsonFile == null) {
	         throw new InvalidParameterException("jsonFile is null.");
	      }
	      object = mapper.readValue(jsonFile, clazz);

	      return object;
	   }
}