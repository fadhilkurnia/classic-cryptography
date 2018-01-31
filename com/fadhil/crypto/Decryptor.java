package com.fadhil.crypto;

import java.nio.ByteBuffer;
import java.io.*;

public class Decryptor {

    private byte[] chiper;
    private String key;
    private int choosenAlgorithm;
    public static Decryptor main;

    public static Decryptor New(int name) {
        main = new Decryptor();
        main.choosenAlgorithm = name;
        return main;
    }

    public Decryptor SetKey(String key) {
        this.key = key.toUpperCase();
        this.key = this.key.replaceAll("\\s+","");
        return this;
    }

    public Decryptor Decrypt(byte[] chiper) {
        this.chiper = chiper;
        if (choosenAlgorithm == Encryptor.VIGENERE_STANDARD) {
            DoVigenereDecrypt();
        } else if (choosenAlgorithm == Encryptor.VIGENERE_EXTENDED) {
            DoVigenereExtendedDecrypt();
        } else {
            DoPlayFairDecrypt();
        }
        return main;
    }

    public void ShowPlain() {
        String plain = new String(chiper);
        System.out.println(plain);
    }

    public void ExportPlain(String outfile) throws IOException {
        FileOutputStream fos = new FileOutputStream(outfile);
        fos.write(chiper);
        fos.close();
    }

    private void DoVigenereDecrypt() {
        int j = 0, i = 0, keyLength = key.length();

        for (i = 0; i < chiper.length; i++) {
            byte diff = (byte)(chiper[i] - key.charAt(j));
            if (diff < 0) {
                diff = (byte) (diff + 26);
            }
            chiper[i] = (byte) ((diff%26) + 65);
            j = (j + 1)%keyLength;
        }
    }

    private void DoVigenereExtendedDecrypt() {
        int j = 0, i = 0, keyLength = key.length();
        for (i = 0; i < chiper.length; i++) {
            int intch = chiper[i] & 0xFF; 
            int diff = intch - key.charAt(j);
            if (diff < 0) {
                diff += 256;
            }
            chiper[i] = (byte) (diff%256);
            j = (j + 1)%keyLength;
        }
    }

    private void DoPlayFairDecrypt() {
        // Prepare key matrix
        key += "ABCDEFGHIKLMNOPQRSTUVWXYZ";
        key = Encryptor.UniqueKey(key);
        char[][] keyMatrix = new char[6][6];
        int k = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                keyMatrix[i][j] = key.charAt(k++);
            }   
        }
        // extend matrix
        for (int i = 0; i < 5; i++) {
            keyMatrix[5][i] = keyMatrix[0][i];
            keyMatrix[i][5] = keyMatrix[i][0];
        }
        keyMatrix[5][5] = ' ';

        // Print matrix
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(keyMatrix[i][j] + " ");
            }   
            System.out.println();
        }

        // Decryption Process 
        int i = 0;
        while (i < chiper.length) {


            i++;
        }
    }

}
