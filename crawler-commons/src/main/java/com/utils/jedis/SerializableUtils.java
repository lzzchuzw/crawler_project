package com.utils.jedis;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 
 * @ClassName: SerializableUtils
 * @Description: 用于对象序列化
 * @author: leisure
 * @param <T>
 * @date: 2018年6月5日 上午10:20:09
 */
public class SerializableUtils<T> {

	/**
	 * 
	 * @Title: serializableEncode
	 * @Description: 将对象序列化编码为byte[]
	 * @param o
	 * @return byte[]
	 * @author leisure
	 * @date 2018年6月5日上午10:20:52
	 */
	public byte[] serializableEncode(T t) {
		if (null == t) {
			return null;
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(bos);
			oos.writeObject(t);
			oos.flush();
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			if (null != oos) {
				try {
					oos.close();
					bos.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}
		return bos.toByteArray();
	}

	/**
	 * 
	 * @Title: serializableDecode
	 * @Description: 序列化解码
	 * @param bytes
	 * @return T
	 * @author leisure
	 * @date 2018年6月5日上午10:27:17
	 */
	@SuppressWarnings("unchecked")
	public T serializableDecode(byte[] bytes) {
		if (null == bytes || 0 == bytes.length) {
			return null;
		}
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		ObjectInputStream ois = null;
		T t = null;
		try {
			ois = new ObjectInputStream(bais);
			t = (T) ois.readObject();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} finally {
			if (null != ois) {
				try {
					ois.close();
					bais.close();
				} catch (IOException e) {

					e.printStackTrace();
				}

			}
		}
		return t;
	}

	/**
	 * 
	 * @Title: serializableObject2HardDisk
	 * @Description: 将对象t序列化保存到本地硬盘
	 * @param t
	 * @param filePath
	 *            保存对象的文件路径
	 * @return String
	 * @author leisure
	 * @date 2018年6月12日上午10:42:06
	 */
	public String serializableObject2HardDisk(T t, final String filePath) {
		if (null == filePath) {
			return null;
		}
		byte[] buffer = serializableEncode(t);
		BufferedOutputStream bos = null;
		try {
			bos = new BufferedOutputStream(new FileOutputStream(filePath));
			bos.write(buffer);
			bos.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != bos) {
				try {
					bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return filePath;
	}
    /**
     * 
    * @Title: readObjectFromSerializableTxt
    * @Description: 从本地硬盘存储的序列化文件中反序列化对象T
    * @param filePath T的序列化文件存储路径
    * @return T
    * @author leisure
    * @date 2018年6月12日上午10:52:25
     */
	public T readObjectFromSerializableTxt(final String filePath) {
		if (null == filePath) {
			return null;
		}
		T t = null;
		BufferedInputStream bis = null;
		ByteArrayOutputStream baos = null;
		baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int count = 0;
		try {
			bis = new BufferedInputStream(new FileInputStream(filePath));
			while (-1 != (count = bis.read(buffer))) {
				baos.write(buffer, 0, count);
				baos.flush();
			}
			t = serializableDecode(baos.toByteArray());
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			if (null != baos) {
				try {
					baos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (null != bis) {
				try {
					bis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return t;
	}
}
