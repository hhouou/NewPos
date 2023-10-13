package com.achpay.wallet.widget.datepicker;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.achpay.wallet.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 说明：自定义时间选择器
 */
public class DatePicker extends FrameLayout implements PickerView.OnSelectListener {

    private Context mContext;
    private Callback mCallback;
    private Calendar mBeginTime, mEndTime, mSelectedTime;

    private PickerView mDpvYear, mDpvMonth, mDpvDay,mDpvHour,mDpvMinute;

    private int mBeginYear, mBeginMonth, mBeginDay, mEndYear, mEndMonth, mEndDay;
    private List<String> mYearUnits = new ArrayList<>(), mMonthUnits = new ArrayList<>(), mDayUnits = new ArrayList<>(), mHourUnits = new ArrayList<>(), mMinuteUnits = new ArrayList<>();
    private DecimalFormat mDecimalFormat = new DecimalFormat("00");

    private boolean mCanShowPreciseTime;


    /**
     * 时间单位的最大显示值
     */

    private static final int MAX_MONTH_UNIT = 12;

    /**
     * 级联滚动延迟时间
     */
    private static final long LINKAGE_DELAY_DEFAULT = 100L;

    public DatePicker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        inflate(getContext(), R.layout.date_picker, this);

        mDpvYear = findViewById(R.id.wheel_year);
        mDpvYear.setOnSelectListener(this);
        mDpvMonth = findViewById(R.id.wheel_month);
        mDpvMonth.setOnSelectListener(this);
        mDpvDay = findViewById(R.id.wheel_day);
        mDpvDay.setOnSelectListener(this);
        mDpvHour = findViewById(R.id.wheel_hour);
        mDpvHour.setOnSelectListener(this);
        mDpvMinute = findViewById(R.id.wheel_minute);
        mDpvMinute.setOnSelectListener(this);

    }


    /**
     * 时间选择结果回调接口
     */
    public interface Callback {
        void onTimeSelected(String timeStr);
    }


    @Override
    public void onSelect(View view, String selected) {
        if (view == null || TextUtils.isEmpty(selected)) return;

        int timeUnit;
        try {
            timeUnit = Integer.parseInt(selected);
        } catch (Throwable ignored) {
            return;
        }

        int id = view.getId();
        if (id == R.id.wheel_year) {
            mSelectedTime.set(Calendar.YEAR, timeUnit);
            linkageMonthUnit(true, LINKAGE_DELAY_DEFAULT);
        } else if (id == R.id.wheel_month) {// 防止类似 2018/12/31 滚动到11月时因溢出变成 2018/12/01
            int lastSelectedMonth = mSelectedTime.get(Calendar.MONTH) + 1;
            mSelectedTime.add(Calendar.MONTH, timeUnit - lastSelectedMonth);
            linkageDayUnit(true, LINKAGE_DELAY_DEFAULT);
        } else if (id == R.id.wheel_day) {
            mSelectedTime.set(Calendar.DAY_OF_MONTH, timeUnit);
        } else if (id == R.id.wheel_hour){
            mSelectedTime.set(Calendar.HOUR_OF_DAY, timeUnit);
        } else if (id == R.id.wheel_minute){
            mSelectedTime.set(Calendar.MINUTE, timeUnit);
        }

        if (mCallback!=null)
        mCallback.onTimeSelected(DateFormatUtils.long2Str(mSelectedTime.getTimeInMillis(),true));

    }

    /**
     * 时间选择监听
     * @param callback
     */
        public void setOnSelectTimeListener(Callback callback) {
            this.mCallback = callback;
        }

    /**
     * 初始化数据
     * @param beginTimestamp 开始时间
     * @param endTimestamp  结算时间
     */
    public void initData(long beginTimestamp,long endTimestamp) {

        mBeginTime = Calendar.getInstance();
        mBeginTime.setTimeInMillis(beginTimestamp);
        mEndTime = Calendar.getInstance();
        mEndTime.setTimeInMillis(endTimestamp);
        mSelectedTime = Calendar.getInstance();




        mSelectedTime.setTimeInMillis(mBeginTime.getTimeInMillis());

        mBeginYear = mBeginTime.get(Calendar.YEAR);
        // Calendar.MONTH 值为 0-11
        mBeginMonth = mBeginTime.get(Calendar.MONTH) + 1;
        mBeginDay = mBeginTime.get(Calendar.DAY_OF_MONTH);


        mEndYear = mEndTime.get(Calendar.YEAR);
        mEndMonth = mEndTime.get(Calendar.MONTH) + 1;
        mEndDay = mEndTime.get(Calendar.DAY_OF_MONTH);


        boolean canSpanYear = mBeginYear != mEndYear;
        boolean canSpanMon = !canSpanYear && mBeginMonth != mEndMonth;
        boolean canSpanDay = !canSpanMon && mBeginDay != mEndDay;


        if (canSpanYear) {
            initDateUnits(MAX_MONTH_UNIT, mBeginTime.getActualMaximum(Calendar.DAY_OF_MONTH));
        } else if (canSpanMon) {
            initDateUnits(mEndMonth, mBeginTime.getActualMaximum(Calendar.DAY_OF_MONTH));
        } else if (canSpanDay) {
            initDateUnits(mEndMonth, mEndDay);
        }
    }

    private void initDateUnits(int endMonth, int endDay) {
        for (int i = mBeginYear; i <= mEndYear; i++) {
            mYearUnits.add(String.valueOf(i));
        }

        for (int i = mBeginMonth; i <= endMonth; i++) {
            mMonthUnits.add(mDecimalFormat.format(i));
        }

        for (int i = mBeginDay; i <= endDay; i++) {
            mDayUnits.add(mDecimalFormat.format(i));
        }

        for (int i = 0; i <= 24; i++) {
            mHourUnits.add(mDecimalFormat.format(i));
        }

        for (int i = 0; i <= 59; i++) {
            mMinuteUnits.add(mDecimalFormat.format(i));
        }


        mDpvYear.setDataList(mYearUnits);
        mDpvYear.setSelected(0);
        mDpvMonth.setDataList(mMonthUnits);
        mDpvMonth.setSelected(0);
        mDpvDay.setDataList(mDayUnits);
        mDpvDay.setSelected(0);

        mDpvHour.setDataList(mHourUnits);
//        mDpvHour.setSelected(0);
        mDpvMinute.setDataList(mMinuteUnits);
//        mDpvMinute.setSelected(0);

        setCanScroll();
    }

    private void setCanScroll() {
        mDpvYear.setCanScroll(mYearUnits.size() > 1);
        mDpvMonth.setCanScroll(mMonthUnits.size() > 1);
        mDpvDay.setCanScroll(mDayUnits.size() > 1);
    }

    /**
     * 联动“月”变化
     *
     * @param showAnim 是否展示滚动动画
     * @param delay    联动下一级延迟时间
     */
    private void linkageMonthUnit(final boolean showAnim, final long delay) {
        int minMonth;
        int maxMonth;
        int selectedYear = mSelectedTime.get(Calendar.YEAR);
        if (mBeginYear == mEndYear) {
            minMonth = mBeginMonth;
            maxMonth = mEndMonth;
        } else if (selectedYear == mBeginYear) {
            minMonth = mBeginMonth;
            maxMonth = MAX_MONTH_UNIT;
        } else if (selectedYear == mEndYear) {
            minMonth = 1;
//            maxMonth = mEndMonth;
            maxMonth = MAX_MONTH_UNIT;
        } else {
            minMonth = 1;
            maxMonth = MAX_MONTH_UNIT;
        }

        // 重新初始化时间单元容器
        mMonthUnits.clear();
        for (int i = minMonth; i <= maxMonth; i++) {
            mMonthUnits.add(mDecimalFormat.format(i));
        }
        mDpvMonth.setDataList(mMonthUnits);

        // 确保联动时不会溢出或改变关联选中值
        int selectedMonth = getValueInRange(mSelectedTime.get(Calendar.MONTH) + 1, minMonth, maxMonth);
        mSelectedTime.set(Calendar.MONTH, selectedMonth - 1);
        mDpvMonth.setSelected(selectedMonth - minMonth);
        if (showAnim) {
            mDpvMonth.startAnim();
        }

        // 联动“日”变化
        mDpvMonth.postDelayed(new Runnable() {
            @Override
            public void run() {
                linkageDayUnit(showAnim, delay);
            }
        }, delay);
    }

    /**
     * 联动“日”变化
     *
     * @param showAnim 是否展示滚动动画
     * @param delay    联动下一级延迟时间
     */
    private void linkageDayUnit(final boolean showAnim, final long delay) {
        int minDay;
        int maxDay;
        int selectedYear = mSelectedTime.get(Calendar.YEAR);
        int selectedMonth = mSelectedTime.get(Calendar.MONTH) + 1;
        if (mBeginYear == mEndYear && mBeginMonth == mEndMonth) {
            minDay = mBeginDay;
            maxDay = mEndDay;
        } else if (selectedYear == mBeginYear && selectedMonth == mBeginMonth) {
            minDay = mBeginDay;
            maxDay = mSelectedTime.getActualMaximum(Calendar.DAY_OF_MONTH);
        } else if (selectedYear == mEndYear && selectedMonth == mEndMonth) {
            minDay = 1;
//            maxDay = mEndDay;
            maxDay = mSelectedTime.getActualMaximum(Calendar.DAY_OF_MONTH);
        } else {
            minDay = 1;
            maxDay = mSelectedTime.getActualMaximum(Calendar.DAY_OF_MONTH);
        }

        mDayUnits.clear();
        for (int i = minDay; i <= maxDay; i++) {
            mDayUnits.add(mDecimalFormat.format(i));
        }
        mDpvDay.setDataList(mDayUnits);

        int selectedDay = getValueInRange(mSelectedTime.get(Calendar.DAY_OF_MONTH), minDay, maxDay);
        mSelectedTime.set(Calendar.DAY_OF_MONTH, selectedDay);
        mDpvDay.setSelected(selectedDay - minDay);
        if (showAnim) {
            mDpvDay.startAnim();
        }

    }


    private int getValueInRange(int value, int minValue, int maxValue) {
        if (value < minValue) {
            return minValue;
        } else if (value > maxValue) {
            return maxValue;
        } else {
            return value;
        }
    }


    /**
     * 设置日期选择器的选中时间
     *
     * @param dateStr  日期字符串
     * @param showAnim 是否展示动画
     * @return 是否设置成功
     */
    public boolean setSelectedTime(String dateStr, boolean showAnim) {
        return !TextUtils.isEmpty(dateStr) && setSelectedTime(DateFormatUtils.str2Long(dateStr, mCanShowPreciseTime), showAnim);
    }


    /**
     * 设置日期选择器的选中时间
     *
     * @param timestamp 毫秒级时间戳
     * @param showAnim  是否展示动画
     * @return 是否设置成功
     */
    public boolean setSelectedTime(long timestamp, boolean showAnim) {

        if (timestamp < mBeginTime.getTimeInMillis()) {
            timestamp = mBeginTime.getTimeInMillis();
        } else if (timestamp > mEndTime.getTimeInMillis()) {
            timestamp = mEndTime.getTimeInMillis();
        }
        mSelectedTime.setTimeInMillis(timestamp);

        mYearUnits.clear();
        for (int i = mBeginYear; i <= mEndYear; i++) {
            mYearUnits.add(String.valueOf(i));
        }
        mDpvYear.setDataList(mYearUnits);
        mDpvYear.setSelected(mSelectedTime.get(Calendar.YEAR) - mBeginYear);

        mDpvHour.setSelected(mSelectedTime.get(Calendar.HOUR_OF_DAY));
        mDpvMinute.setSelected(mSelectedTime.get(Calendar.MINUTE));
        linkageMonthUnit(showAnim, showAnim ? LINKAGE_DELAY_DEFAULT : 0);
        return true;
    }


    /**
     * 设置日期控件是否可以循环滚动
     */
    public void setScrollLoop(boolean canLoop) {
        mDpvYear.setCanScrollLoop(canLoop);
        mDpvMonth.setCanScrollLoop(canLoop);
        mDpvDay.setCanScrollLoop(canLoop);
    }

    /**
     * 设置日期控件是否展示滚动动画
     */
    public void setCanShowAnim(boolean canShowAnim) {
        mDpvYear.setCanShowAnim(canShowAnim);
        mDpvMonth.setCanShowAnim(canShowAnim);
        mDpvDay.setCanShowAnim(canShowAnim);

    }

    /**
     * 销毁弹窗
     */
    public void onDestroy() {
        mDpvYear.onDestroy();
        mDpvMonth.onDestroy();
        mDpvDay.onDestroy();
    }

}
