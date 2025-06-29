package com.design.portfolio;

import com.design.portfolio.dto.valueobjects.ImageType;
import com.design.portfolio.entity.MediaItemEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Log4j2
@SpringBootApplication

public class PortfolioBeApplication implements CommandLineRunner {


    public static void main(String[] args) {

        SpringApplication.run(PortfolioBeApplication.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		List<MediaItemEntity> images = new ArrayList<>();
		int groupIndex = 0;
		int rowIndex = 0;
		int columnIndex = 0;
		int imageIndex = 0;

		File mediaFolder = new ClassPathResource("static/media").getFile();

		File[] files = mediaFolder.listFiles((dir, name) ->
				name.toLowerCase().endsWith(".png") ||
						name.toLowerCase().endsWith(".jpg") ||
						name.toLowerCase().endsWith(".jpeg"));

		Arrays.sort(files, Comparator.comparingInt(f -> Integer.parseInt(f.getName().replaceAll("\\D+", ""))));
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				File file = files[i];
				String fileName = file.getName();
				String fileType = getFileExtension(fileName);
				BufferedImage img = ImageIO.read(file);
				if (img == null) continue;

				// Her 15 fotoğrafta bir grup değiştir
				if (imageIndex % 15 == 0 && imageIndex != 0) {
					groupIndex++;
				}

				// 3 sütunlu grid sistemi
				rowIndex = (imageIndex % 15) / 3;      // 0-4
				columnIndex = imageIndex % 3;          // 0-2

				images.add(new MediaItemEntity(
						(long) imageIndex,
						fileName,
						"Title " + imageIndex,
						"Lacin",
						LocalDate.now().toString(),
						"resource/static/media/" + fileName,
						groupIndex,
						rowIndex,
						columnIndex
				));

				log.info("Added image " + imageIndex + " to group " + groupIndex + " [row=" + rowIndex + ", col=" + columnIndex + "]");
				imageIndex++;
			}
		}

		// INSERT SQL dosyası yazımı
		if (!images.isEmpty()) {
			FileWriter myWriter = new FileWriter("C:\\WorkSpace\\DesignerPortfolioProject\\portfolio-be\\src\\main\\resources\\data.sql");

			for (MediaItemEntity mediaItemEntity : images) {
				try {
					myWriter.write("INSERT INTO MEDIA_ITEM_ENTITY (id, image_name, title, author, published_date, image_path, group_index, row_index, column_index) VALUES (" +
							mediaItemEntity.getId() + ", '" +
							mediaItemEntity.getImageName() + "', '" +
							mediaItemEntity.getTitle() + "', '" +
							mediaItemEntity.getAuthor() + "', '" +
							mediaItemEntity.getPublishedDate() + "', '" +
							mediaItemEntity.getImagePath() + "', " +
							mediaItemEntity.getGroupIndex() + ", " +
							mediaItemEntity.getRowIndex() + ", " +
							mediaItemEntity.getColumnIndex() + ");\n");

				} catch (IOException e) {
					System.out.println("An error occurred while writing SQL.");
					e.printStackTrace();
				}
			}
			myWriter.close();
			System.out.println("SQL file successfully generated.");
		}
	}
	private String determineRatioType ( int width, int height){
		if (height > width)
			return ImageType.VERTICAL.name();
		else if (width > height)
			return ImageType.HORIZONTAL.name();
		else
			return ImageType.NORMAL.name();
	}

	private String getFileExtension(String fileName) {
		int lastDot = fileName.lastIndexOf('.');
		return lastDot != -1 ? fileName.substring(lastDot + 1).toLowerCase() : "";
	}

	private String encodeToBase64(File file, String fileType) throws IOException {
		try (InputStream is = new FileInputStream(file)) {
			byte[] bytes = is.readAllBytes();
			String base64 = Base64.getEncoder().encodeToString(bytes);
			return "data:image/" + fileType + ";base64," + base64;
		}
	}
}

