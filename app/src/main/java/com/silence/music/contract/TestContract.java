package com.silence.music.contract;

import com.silence.music.base.ibase.IView;
import com.silence.music.bean.TestBean;

/**
 * @author tinlone
 * @date 2017/10/19 0019.
 */

public class TestContract {

    public interface ITestView extends IView<TestBean>{

    }

    public interface ITestPresenter {
        /**
         * 获取测试数据
         * @param id
         */
        void getTestData(long id);
    }

}
