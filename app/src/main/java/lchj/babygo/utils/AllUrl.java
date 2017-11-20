package lchj.babygo.utils;

/**
 * Created by XiMiTwo on 2017/10/24.
 */

public class AllUrl {
    //首页
    public static final String INDEX_URL ="http://39.108.117.228/RestServer/api/index.php" ;
    //分类左侧
    public static final String SORT_LIST ="http://39.108.117.228/RestServer/api/sort_list.php" ;
    //分类右侧
    public static final String BASE_SORT_CONTENT_LIST="http://39.108.117.228/RestServer/api/sort_content_list.php";
    public static final String SORT_CONTENT_LIST = BASE_SORT_CONTENT_LIST+"?contentId=";
    public static final String SORT_CONTENT_LISTS ="http://952cloud.top/RestServer/api/sort_content_list.php?contentId=";
    //搜索
    public static final String SEARCH ="http://39.108.117.228/RestServer/api/search.php" ;
    public static final String SEARCHS =SEARCH+"?key=";
    //下拉刷新和上拉加载
    public static final String REFRESH ="http://39.108.117.228/RestServer/api/refresh.php" ;
    public static final String REFRESHS = REFRESH+"?index=";
    /**
     * 注册
     */
    public static final String USER_PROFILE="http://39.108.117.228/RestServer/api/user_profile.php" ;
    //发现webView链接
    public static final String DISCOVER ="http://www.ximitwo.com/";
    //购物车
    public static final String SHOP_CART ="http://39.108.117.228/RestServer/api/shop_cart.php" ;
    //购物车加减
    public static final String SHOP_CART_COUNT ="http://39.108.117.228/RestServer/api/shop_cart_count.php" ;
    public static final String ORDER_LIST ="http://39.108.117.228/RestServer/api/order_list.php";
    //收货地址
    public static final String ADDRESS ="http://39.108.117.228/RestServer/api/address.php";
    //关于
    public static final String ABOUT ="http://39.108.117.228/RestServer/api/about.php";
    //商品详情
    public static final String GOODS_DETAIL ="http://39.108.117.228/RestServer/api/goods_detail.php";
    //添加购物车数量
    public static final String ADD_SHOP_CART_COUNT ="http://39.108.117.228/RestServer/api/add_shop_cart_count.php";


}
