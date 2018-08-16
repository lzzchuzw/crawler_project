package com.mi.timer;

import java.util.List;
import java.util.Map;

import com.mi.address.XiaomiAddressManager;
import com.mi.product.XiaomiProductInfoManager;
import com.mi.shoppingCart.XiaomiShoppingCartManager;
import com.mi.shoppingRush.XiaomiShoppingRush;
import com.utils.request.HttpClientRequestHandler;
import com.utils.request.RequestConstant;


public class ShoppingRushRunnable  implements Runnable {
    
	private HttpClientRequestHandler requestHandler;
	
	public ShoppingRushRunnable(HttpClientRequestHandler requestHandler) {
		this.requestHandler = requestHandler;
	}
	@Override
	public void run() {
		XiaomiProductInfoManager xiaomiProductInfoManager = new XiaomiProductInfoManager();
		List<String> goodsIdList = xiaomiProductInfoManager.getGoodIdOfProduct(requestHandler, RequestConstant.PRODUCT_ID);
		//抢购相关方法的测试
		XiaomiShoppingRush xiaomiShoppingRush = new XiaomiShoppingRush();
		Map<String,Object> shoppingRushParamtersMap = xiaomiShoppingRush.fetchShoppingRushParamters(requestHandler, RequestConstant.PRODUCT_ID);
		xiaomiShoppingRush.fetchShoppingRushSkipUrl(requestHandler, shoppingRushParamtersMap);
		//将商品添加到购物车中
		XiaomiShoppingCartManager xiaomiShoppingCartManager = new XiaomiShoppingCartManager();
		
		xiaomiShoppingCartManager.prepareForAddProductToCart(requestHandler, RequestConstant.PRODUCT_ID,goodsIdList.get(0));
		xiaomiShoppingCartManager.addProductToCart(requestHandler, RequestConstant.PRODUCT_ID, goodsIdList.get(0));
		//进入购物车
		xiaomiShoppingCartManager.gotoShoppingCartHomePage(requestHandler);
		xiaomiShoppingCartManager.getProductListInCart(requestHandler);
		
		xiaomiShoppingCartManager.checkoutPreCheck(requestHandler);
		Map<String,Object> map = xiaomiShoppingCartManager.checkoutOrder(requestHandler);
		XiaomiAddressManager xiaomiAddressManager = new XiaomiAddressManager();
		List<String> list = xiaomiAddressManager.fetchAddressIdList(requestHandler);
		map.put("addressId", list.get(0));
		xiaomiShoppingCartManager.submitOrder(requestHandler, map);
		
	}
	
	
	
	
	
	public HttpClientRequestHandler getRequestHandler() {
		return requestHandler;
	}
	public void setRequestHandler(HttpClientRequestHandler requestHandler) {
		this.requestHandler = requestHandler;
	}

}
