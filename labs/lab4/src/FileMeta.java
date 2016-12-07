import jsocket.util.ByteUtil;
import jsocket.util.FilterFunctionType;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * @author Will Czifro
 */
public class FileMeta {

    public String filename;

    public long filesize;

    public FileMeta(byte[] rawData, int filenameLength) {
        // FIXME
    }

    public FileMeta() {}

    public byte[] getBytes() {
        return null; // FIXME
    }

    private String parseBytes(byte[] bytes) {
        return FilterFunctionType.NULL_CHARS.getFunc().apply(new String(bytes));
    }

    private long getLong(byte[] bytes) {
        return ByteBuffer.wrap(bytes).getLong();
    }
}
