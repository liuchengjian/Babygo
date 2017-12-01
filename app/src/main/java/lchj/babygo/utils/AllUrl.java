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

    //活动详情
    public static final String ACTIVITY_DETAIL ="http://39.108.117.228/BabyGoServer/api/activity_profile.php";
    //活动行程
    public static final String ACTIVITY_TRIP ="http://39.108.117.228/BabyGoServer/api/activity_trip.php";

    //活动详情
    public static final String ARTICLE_DETAIL ="http://39.108.117.228/BabyGoServer/api/article_profile.php";

}
