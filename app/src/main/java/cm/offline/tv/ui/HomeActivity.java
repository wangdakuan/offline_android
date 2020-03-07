/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package cm.offline.tv.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import cm.offline.tv.R;
import cm.offline.tv.event.MessageEvent;
import cm.offline.tv.ui.right.RightAdvertisingFragment;
import cm.offline.tv.ui.right.RightCustomMadeFragment;
import cm.offline.tv.utils.FragmentUtils;

/**
 * Copyright (C), 2015-2020, 湖南靠谱科技股份有限公司
 * FileName: AdvertisingPage
 * Author: wangdakuan
 * Date: 2020-03-06 15:03
 * Description: 首页
 * History:
 * version：1.5
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.main_right_fragment)
    FrameLayout mMainRightFragment;

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_home);
        ButterKnife.bind(this);
        FragmentUtils.replaceFragment(getSupportFragmentManager(), new RightAdvertisingFragment(), R.id.main_right_fragment, false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPageEvent(MessageEvent event) {
        Log.e("---------------", "-----onPageEvent--MessageEvent--|==");
        if (event.mEventKey == MessageEvent.START_CUSTOM_PAGE) {
            FragmentUtils.replaceFragment(getSupportFragmentManager(), new RightCustomMadeFragment(), R.id.main_right_fragment, false);
        } else if (event.mEventKey == MessageEvent.START_ADVERTISING_PAGE) {
            finish();
        }
    }
}
