package com.zxing.encoding;

import java.util.Hashtable;

import android.graphics.Bitmap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
/**
 * @author Ryan Tang
 *
 */
public final class EncodingHandler
{
	private  static int  COLOR = 0xff0000000;
	private  static int  BACKGROUND = 0xfffffffff;
	public static Bitmap createQRCode(String str,int widthAndHeight,int Color,int BackgroundColor) throws WriterException{
		COLOR=Color;
		BACKGROUND=BackgroundColor;
		return createQRCode(str,widthAndHeight);
	}
	public static Bitmap createQRCode(String str, int widthAndHeight) throws WriterException{

		Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();  

        hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); 

		BitMatrix matrix = new MultiFormatWriter().encode(str,

														  BarcodeFormat.QR_CODE, widthAndHeight, widthAndHeight);

		int width = matrix.getWidth();

		int height = matrix.getHeight();

		int[] pixels = new int[width * height];



		for(int y = 0; y < height; y++){

			for(int x = 0; x < width; x++){

				if(matrix.get(x, y)){


					pixels[y * width + x] = COLOR;

				}
				else{


					pixels[y * width + x] = BACKGROUND;

				}

			}

		}

		Bitmap bitmap = Bitmap.createBitmap(width, height,

											Bitmap.Config.RGB_565);

		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);

		return bitmap;

	}
}
