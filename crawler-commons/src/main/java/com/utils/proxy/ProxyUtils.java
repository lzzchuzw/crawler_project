package com.utils.proxy;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpHost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.DnsResolver;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContexts;



public class ProxyUtils {
	
	private String proxyHost = "127.0.0.1";  
    private int proxyPort = 1080;
    private String proxyName = null;
    private String proxyPwd = null; 
    
    private PoolingHttpClientConnectionManager connectingManager;
    private InetSocketAddress socketProxy;
    
    public ProxyUtils() {
    	
    }
    public ProxyUtils(String proxyHost,int proxyPort,String proxyName,String proxyPwd) {
    	this.proxyHost = proxyHost;
    	this.proxyPort = proxyPort;
    	this.proxyName = proxyName;
    	this.proxyPwd = proxyPwd;
    	
    	this.socketProxy = new InetSocketAddress(proxyHost, proxyPort);
    	
    }
    
    public PoolingHttpClientConnectionManager generateConnectionManager() {
    	/* SSLConnectionSocketFactory socketFactory = null;
		try {
			socketFactory = new SSLConnectionSocketFactory(
			         SSLContext.getDefault(),
			         new String[] {"SSLv2Hello","SSLv3","TLSv1","TLSv1.1","TLSv1.2"},
			         null,
			         SSLConnectionSocketFactory.getDefaultHostnameVerifier());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 
    	 Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                 .register("http", PlainConnectionSocketFactory.getSocketFactory())
                 .register("https", socketFactory)
                 .build();*/
    	 
    	 
    	Registry<ConnectionSocketFactory> reg = RegistryBuilder.<ConnectionSocketFactory> create()  
                .register("http", new MyConnectionSocketFactory())  
                .register("https", new MySSLConnectionSocketFactory(SSLContexts.createSystemDefault())).build();  
          
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(reg, new FakeDnsResolver()); 
        
        return cm;
    }
    
    private class FakeDnsResolver implements DnsResolver {  
        @Override  
        public InetAddress[] resolve(String host) throws UnknownHostException {  
            // Return some fake DNS record for every request, we won't be using it  
            return new InetAddress[] { InetAddress.getByAddress(new byte[] { 1, 1, 1, 1 }) };  
        }  
    }  
    private class MyConnectionSocketFactory extends PlainConnectionSocketFactory {  
        @Override  
        public Socket createSocket(final HttpContext context) throws IOException {  
            InetSocketAddress socksaddr = (InetSocketAddress) context.getAttribute("socks.address");  
            Proxy proxy = new Proxy(Proxy.Type.SOCKS, socksaddr);  
            return new Socket(proxy);  
        }  
  
        @Override  
        public Socket connectSocket(int connectTimeout, Socket socket, HttpHost host, InetSocketAddress remoteAddress,  
                                    InetSocketAddress localAddress, HttpContext context) throws IOException {  
            // Convert address to unresolved  
            InetSocketAddress unresolvedRemote = InetSocketAddress  
                    .createUnresolved(host.getHostName(), remoteAddress.getPort());  
            return super.connectSocket(connectTimeout, socket, host, unresolvedRemote, localAddress, context);  
        }  
    } 
    
    class MySSLConnectionSocketFactory extends SSLConnectionSocketFactory {  
  	  
        public MySSLConnectionSocketFactory(final SSLContext sslContext) {  
           
            super(sslContext, ALLOW_ALL_HOSTNAME_VERIFIER);  
        }  
  
        @Override  
        public Socket createSocket(final HttpContext context) throws IOException {  
            InetSocketAddress socksaddr = (InetSocketAddress) context.getAttribute("socks.address");  
            Proxy proxy = new Proxy(Proxy.Type.SOCKS, socksaddr);  
            return new Socket(proxy);  
        }  
  
        @Override  
        public Socket connectSocket(int connectTimeout, Socket socket, HttpHost host, InetSocketAddress remoteAddress,  
                                    InetSocketAddress localAddress, HttpContext context) throws IOException {  
            // Convert address to unresolved  
            InetSocketAddress unresolvedRemote = InetSocketAddress  
                    .createUnresolved(host.getHostName(), remoteAddress.getPort());  
            return super.connectSocket(connectTimeout, socket, host, unresolvedRemote, localAddress, context);  
        }  
    }  
    
    /*****************************************************************************************************************/
	public String getProxyHost() {
		return proxyHost;
	}
	public void setProxyHost(String proxyHost) {
		this.proxyHost = proxyHost;
	}
	public int getProxyPort() {
		return proxyPort;
	}
	public void setProxyPort(int proxyPort) {
		this.proxyPort = proxyPort;
	}
	public String getProxyName() {
		return proxyName;
	}
	public void setProxyName(String proxyName) {
		this.proxyName = proxyName;
	}
	public String getProxyPwd() {
		return proxyPwd;
	}
	public void setProxyPwd(String proxyPwd) {
		this.proxyPwd = proxyPwd;
	}
	public PoolingHttpClientConnectionManager getConnectingManager() {
		return connectingManager;
	}
	public void setConnectingManager(PoolingHttpClientConnectionManager connectingManager) {
		this.connectingManager = connectingManager;
	}
	public InetSocketAddress getSocketProxy() {
		return socketProxy;
	}
	public void setSocketProxy(InetSocketAddress socketProxy) {
		this.socketProxy = socketProxy;
	}

}
