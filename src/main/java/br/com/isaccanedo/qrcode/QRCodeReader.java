package br.com.isaccanedo.qrcode;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class QRCodeReader {

    private static String decodeQRCode(File qrCodeimage) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(qrCodeimage);
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        try {
            Result result = new MultiFormatReader().decode(bitmap);
            return result.getText();
        } catch (NotFoundException e) {
            System.out.println("Não há código QR na imagem");
            return null;
        }
    }

    public static void main(String[] args) {
        try {
            File file = new File("./MyQRCode.png");
            String decodedText = decodeQRCode(file);
            if(decodedText == null) {
                System.out.println("Nenhum código QR encontrado na imagem");
            } else {
                System.out.println("Decoded text = " + decodedText);
            }
        } catch (IOException e) {
            System.out.println("Não foi possível decodificar o código QR, IOException :: " + e.getMessage());
        }
    }
}