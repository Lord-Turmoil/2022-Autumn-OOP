package scs.util;

import scs.common.ErrorType;

import java.io.*;

public class Serializer {
	public static ErrorType serialize(Object obj, String dir) {
		if (!FileUtil.isValid(dir)) {
			return ErrorType.INVALID_FILENAME;
		}

		if (!FileUtil.exists(dir)) {
			try {
				FileUtil.mkdir(dir);
			} catch (IOException e) {
				return ErrorType.FAILED_TO_CREATE_FILE;
			}
		}

		File file = new File(dir);
		if (!file.exists()) {
			// This shouldn't happen.
			return ErrorType.INVALID_FILENAME;
		}

		OutputStream outputStream = null;
		ObjectOutputStream objectOutputStream = null;

		try {
			outputStream = new FileOutputStream(file);
			objectOutputStream = new ObjectOutputStream(outputStream);
			objectOutputStream.writeObject(obj);

			objectOutputStream.close();
			outputStream.close();
		} catch (IOException e) {
			return ErrorType.FILE_OPERATION_FAILED;
		}

		return ErrorType.SUCCESS;
	}

	public static Object deserialize(String dir) {
		InputStream inputStream = null;
		ObjectInputStream objectInputStream = null;

		if (!FileUtil.isFile(dir)) {
			return ErrorType.INVALID_FILENAME;
		}

		File file = new File(dir);
		Object obj = null;
		try {
			inputStream = new FileInputStream(file);
			objectInputStream = new ObjectInputStream(inputStream);

			obj = objectInputStream.readObject();

			objectInputStream.close();
			inputStream.close();
		} catch (FileNotFoundException e) {
			return ErrorType.INVALID_FILENAME;
		} catch (IOException e) {
			return ErrorType.FILE_OPERATION_FAILED;
		} catch (ClassNotFoundException e) {
			return ErrorType.CLASS_NOT_FOUND;
		}

		return obj;
	}
}
