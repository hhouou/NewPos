package com.achpay.wallet.ui.activity;


import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import androidx.recyclerview.widget.RecyclerView;

import com.achpay.wallet.R;
import com.achpay.wallet.base.BaseActivity;
import com.achpay.wallet.base.Contract.TransactionSummaryContract;
import com.achpay.wallet.model.http.response.TradeSummaryReponse;
import com.achpay.wallet.presenter.TransactionSummaryPresenter;
import com.achpay.wallet.ui.rate.ExchangeRateActivity;
import com.achpay.wallet.util.StrUtils;
import com.achpay.wallet.widget.datepicker.CustomDatePicker;
import com.achpay.wallet.widget.datepicker.DateFormatUtils;
import com.achpay.wallet.widget.datepicker.DatePicker;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by  on 2021/10/10.
 */

public class TransactionSummaryActivity extends BaseActivity<TransactionSummaryPresenter> implements TransactionSummaryContract.View {


    @BindView(R.id.date_picker_start)
    CustomDatePicker datePickerStart;
    @BindView(R.id.date_picker_end)
    CustomDatePicker datePickerEnd;
    @BindView(R.id.et_start_time)
    EditText etStartTime;
    @BindView(R.id.et_end_time)
    EditText etEndTime;

    private String startTime, endTime;


    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_transaction_summary;
    }

    @Override
    protected void initEventAndData() {


        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, -12);
        int NN = cal.get(Calendar.MINUTE);

        cal.add(Calendar.MINUTE,-NN);
        //用date对象获取日期
        long selectTimestamp = cal.getTimeInMillis();

        long beginTimestamp = DateFormatUtils.str2Long("2022-01-01 00:00:00", true);

        cal.add(Calendar.HOUR_OF_DAY, 13);
        long endTimestamp = cal.getTimeInMillis();


        etStartTime.setText(DateFormatUtils.long2Str(selectTimestamp, true));
        startTime = etStartTime.getText().toString().trim();


        etEndTime.setText(DateFormatUtils.long2Str(endTimestamp, true));
        endTime = etEndTime.getText().toString().trim();


        datePickerStart.initData(beginTimestamp, endTimestamp);
        datePickerStart.setScrollLoop(false);
        datePickerStart.setCanShowAnim(false);

        datePickerStart.setSelectedTime(selectTimestamp, true);

        datePickerStart.setOnSelectTimeListener(new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(String timeStr) {

                if (StrUtils.timeCompare(endTime, timeStr) == 3) {
                    datePickerStart.setSelectedTime(startTime, true);
                    return;
                }

                etStartTime.setText(timeStr);
                startTime = timeStr;



            }
        });


        datePickerEnd.initData(beginTimestamp, endTimestamp);

        datePickerEnd.setScrollLoop(false);
        datePickerEnd.setCanShowAnim(false);

        datePickerEnd.setSelectedTime(endTimestamp, true);

        datePickerEnd.setOnSelectTimeListener(new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(String timeStr) {

                if (StrUtils.timeCompare(startTime, timeStr) == 1) {
                    datePickerEnd.setSelectedTime(endTime, true);
                    return;
                }

                etEndTime.setText(timeStr);
                endTime = timeStr;

            }
        });


    }


    @OnClick({R.id.et_start_time, R.id.et_end_time, R.id.tv_confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_start_time:
                datePickerStart.setVisibility(View.VISIBLE);
                datePickerEnd.setVisibility(View.GONE);
                break;
            case R.id.et_end_time:
                datePickerStart.setVisibility(View.GONE);
                datePickerEnd.setVisibility(View.VISIBLE);

                break;
            case R.id.tv_confirm:

                String startTime = etStartTime.getText().toString().trim();
                String endTime = etEndTime.getText().toString().trim();
                Intent intent = new Intent(this, SummaryActivity.class);
                intent.putExtra("startTime", startTime);
                intent.putExtra("endTime", endTime);
                startActivity(intent);
                finish();
                break;

        }
    }

    @Override
    public void onSummarySeccuss(TradeSummaryReponse reponse) {

    }
}