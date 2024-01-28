package com.example.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class Qrcode {

	private static Logger logger = LoggerFactory.getLogger(Qrcode.class);
	
	private static final String QRCODE_UPLOAD_PATH = "C:/Users/NTPU/git/ManagementSystem/src/main/webapp/images/qrcodes"; // window - C:/qrcodes
	
	static {
		Path dir = Paths.get(QRCODE_UPLOAD_PATH);
        if (!Files.exists(dir)) {
            try {
                Files.createDirectories(dir);
            } catch (IOException e) {
                // Handle the exception according to your requirements
                e.printStackTrace();
            }
        }
        System.out.println("QRCODE_UPLOAD_PATH: "+dir.toAbsolutePath().toString());
        logger.info("QRCODE_UPLOAD_PATH: "+dir.toAbsolutePath().toString());
	}
	
	public static String generateQRcode(String uuid) throws WriterException, IOException{
		String path = String.format("%s/%s.png", QRCODE_UPLOAD_PATH, uuid);
		String charset = "UTF-8";
		Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<>();
		hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		generateQRcode(uuid, path, charset, hashMap, 200, 200);
		return path;
	}
	
	public static void generateQRcode(String data, String path, String charset, Map map, int h, int w)
			throws WriterException, IOException {
		BitMatrix matrix = new MultiFormatWriter()
				.encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, w, h);
		MatrixToImageWriter.writeToPath(matrix, path.substring(path.lastIndexOf('.') + 1), Paths.get(path));
	}

	public static String readQRcode(String path, String charset)
			throws FileNotFoundException, IOException, NotFoundException {
		BinaryBitmap binaryBitmap = new BinaryBitmap(
				new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(new FileInputStream(path)))));
		Result rslt = new MultiFormatReader().decode(binaryBitmap);
		return rslt.getText();

	}

	public static void main(String args[]) throws WriterException, IOException, NotFoundException {

//		// 製作 QR Code
//		String str = UUID.randomUUID().toString();
//		String path = "C:/qrcodes/123456789.png";
//		String charset = "UTF-8";
//		Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<>();
//		hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
//		generateQRcode(str, path, charset, hashMap, 200, 200);
//		System.out.println("QR Code created successfully.");
//
//		// 顯示 QR Code 資訊
//		String qrCodeString = readQRcode(path, charset);
//		System.out.println(qrCodeString);
		
		String uuid = UUID.randomUUID().toString();
		String path = Qrcode.generateQRcode(uuid);
		System.out.println(uuid);
		System.out.println(path);
	}
}
