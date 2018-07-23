package com.utils.request;
/**
 * 
 * @Description Http返回状态码
 * @author lzz
 * @time 2018年2月10日 上午9:45:26
 */
public enum StatusCode {
	
	//Informational 1xx
	CONTINUE(100),//Continue
	SWITCHING_PROTOCOLS(101),
	//Successful 2xx
	OK(200),
	CREATED(201),
	ACCEPTED(202),
	NON_AUTHORITATIVE_INFORMATION(203),
	NO_CONTENT(204),
	RESET_CONTENT(205),
	PARTIAL_CONTENT(206),
	//Redirection 3xx
	MULTIPLE_CHOICES(300),
	MOVED_PERMANENTLY(301),
	FOUND(302),
	SEE_OTHER(303),
	NOT_MODIFIED(304),
	USE_PROXY(305),
	UNUSED(306),
	TEMPORARY_REDIRECT(307),
	//Client Error 4xx
	BAD_REQUEST(400),
	UNAUTHORIZED(401),
	PAYMENT_REQUIRED(402),
	FORBIDDEN(403),
	NOT_FOND(404),
	METHOD_NOT_ALLOWED(405),
	NOT_ACCEPTABLE(406),
	PROXY_AUTHENTICATION_REQUIRED(407),
	REQUEST_TIMEOUT(408),
	CONFLICT(409),
	GONE(410),
	LENTH_REQUIRED(411),
	PROCONDITION_FAILED(412),
	REQUEST_ENTITY_TOO_LARGE(413),
	REQUEST_URI_TOO_LONG(414),
	UNSUPPORTED_MEDIA_TYPE(415),
	REQUESTED_RANGE_NOT_SATISFIABLE(416),
	EXPECATATION_FAILED(417),
	//Server Error 5xx
	INTERNAL_SERVER_ERROR(500),
	NOT_IMPLEMENTED(501),
	BAD_GATEWAY(502),
	SERVICE_UNAVAILABLE(503),
	GATEWAY_TIMEOUT(504),
	HTTP_VERSION_NOT_SUPPORTED(505);
	
	private int value;
	
	private StatusCode(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}

}
