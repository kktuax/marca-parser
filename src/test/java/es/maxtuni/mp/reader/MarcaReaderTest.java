package es.maxtuni.mp.reader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import es.maxtuni.mp.model.Calendar;
import es.maxtuni.mp.model.Match;

public class MarcaReaderTest {

	@Test
	public void testParse() throws Exception {
		Map<String, String> mappings = new HashMap<>();
		mappings.put("Racing", "Racing Santander");
		try(InputStream is = getClass().getResourceAsStream("/calendar-2394092156733122534.html")){
			Calendar calendar = new MarcaReader("Test", mappings).read(is);
			Assert.assertFalse(calendar.getMatches().isEmpty());
			Assert.assertEquals(42, calendar.getMatches().stream()
				.collect(Collectors.groupingBy(Match::getRound))
				.keySet()
				.size()
			);
			Assert.assertEquals(42, calendar.getMatches().stream()
				.filter(m -> m.hasTeam("Racing Santander"))
				.count()
			);
		}
	}
	
}
