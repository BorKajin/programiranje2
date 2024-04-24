package kvizi;

public class Kviz3 {
    public static void main(String[] args) {
        System.out.println(bsdChecksum("viri/gesla.txt"));
    }

    static int bsdChecksum(String filename) {
        int checksum = 0;
        try (var fis = new java.io.FileInputStream(filename)) {
            while (true) {
                int b = fis.read();
                if (b == -1) break;
                checksum = (checksum >> 1) + ((checksum & 1) << 15);
                checksum += b;
                checksum &= 0xffff;
            }

        } catch (java.io.FileNotFoundException e) {
            return 0;
        } catch (Exception ignored) {
        }
        return checksum;
    }
}
