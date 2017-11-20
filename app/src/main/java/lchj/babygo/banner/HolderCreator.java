package lchj.babygo.banner;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * Created by
 */

public class HolderCreator  implements CBViewHolderCreator<ImageHolder> {
    @Override
    public ImageHolder createHolder() {
        return new ImageHolder();
    }
}
