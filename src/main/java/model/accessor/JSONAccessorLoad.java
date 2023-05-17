package model.accessor;

import java.io.FileReader;
import java.io.IOException;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import net.minidev.json.JSONArray;

import model.BitmapItem;
import model.Presentation;
import model.Slide;
import model.SlideItem;
import model.TextItem;

public class JSONAccessorLoad extends AccessorLoad{
    @Override
	public void loadFile(Presentation presentation, String fileName) throws IOException {
		try {			
			FileReader reader = new FileReader(fileName);

			JSONParser parser = new JSONParser();
			JSONObject jsonObj = (JSONObject) parser.parse(reader);

			String title = (String) jsonObj.get(SLIDETITLE);

			JSONArray slides = (JSONArray) jsonObj.get("slides");

			presentation.setTitle(title);

			max = slides.size();

			for (slideNumber = 0; slideNumber < max; slideNumber++) {

				Slide slide = new Slide();

				JSONObject oneSlide = (JSONObject) slides.get(slideNumber);
				slide.setTitle((String) oneSlide.get(SLIDETITLE));

				JSONArray allSlides = (JSONArray) oneSlide.get("items");

				maxItems = allSlides.size();

				for (itemNumber = 0; itemNumber < maxItems; itemNumber++) {

					JSONObject eachItem = (JSONObject) allSlides.get(itemNumber);
			

					SlideItem slideItem = null;

					Integer level = (Integer) eachItem.get(LEVEL);

					String text = (String) eachItem.get(TEXT);

					String kind = (String) eachItem.get(KIND);

					if (kind.equals("image")) {
						slideItem = new BitmapItem(level, text);
					} else {
						slideItem = new TextItem(level, text);
					}

					slide.append(slideItem);

				}
				presentation.append(slide);
			}

		} catch (IOException e) {
			e.printStackTrace();

		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
    
    
}
