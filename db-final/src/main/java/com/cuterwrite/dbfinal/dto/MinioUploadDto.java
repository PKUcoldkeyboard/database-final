package com.cuterwrite.dbfinal.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**  
 * minio上传文件参数
 * @author Pang S.Z.
 * @create 2020-10-07 23:34:08 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MinioUploadDto {
	private String name;
	private String url;
}
