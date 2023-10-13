package com.achpay.wallet.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.achpay.wallet.R;
import com.achpay.wallet.util.NumberUtils;
import com.achpay.wallet.util.StrUtils;

public class KeyboardView extends LinearLayout implements View.OnClickListener {

    private TextView buttonCifirm;
    private ImageView buttonDelete;
    private Context mContext;
    private TextChangeListener textChangeListener;
    private StringBuilder inputStr = new StringBuilder();
    private int index;
    private int nDecimal = 4; //默认两位小数

    public KeyboardView(Context context) {
        super(context);
    }


    public KeyboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }

    public KeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        View.inflate(context, R.layout.view_keyboard, this);

        TextView button1 = findViewById(R.id.input_index_1);
        TextView button2 = findViewById(R.id.input_index_2);
        TextView button3 = findViewById(R.id.input_index_3);
        TextView button4 = findViewById(R.id.input_index_4);
        TextView button5 = findViewById(R.id.input_index_5);
        TextView button6 = findViewById(R.id.input_index_6);
        TextView button7 = findViewById(R.id.input_index_7);
        TextView button8 = findViewById(R.id.input_index_8);
        TextView button9 = findViewById(R.id.input_index_9);
        TextView button0 = findViewById(R.id.input_index_0);
        TextView button00 = findViewById(R.id.input_index_00);
        TextView buttonPoint = findViewById(R.id.input_index_point);
        buttonDelete = (ImageView) findViewById(R.id.input_index_delete);
        buttonCifirm = findViewById(R.id.checkout_receipt_confirm);

        button0.setOnClickListener(this);
        button00.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        buttonPoint.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
        buttonCifirm.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (textChangeListener == null) return;
        switch (view.getId()) {
            case R.id.input_index_0:
            case R.id.input_index_00:
            case R.id.input_index_1:
            case R.id.input_index_2:
            case R.id.input_index_3:
            case R.id.input_index_4:
            case R.id.input_index_5:
            case R.id.input_index_6:
            case R.id.input_index_7:
            case R.id.input_index_8:
            case R.id.input_index_9:
            case R.id.input_index_point:
                String txt = ((TextView) view).getText().toString();
                if (StrUtils.StrToLong(inputStr.toString())>99999999999L)return;
                
                setAmount(txt);
                setOkBackgroundColor();


                textChangeListener.changeText(inputStr.toString());
                index++;
//                textChangeListener.addText(index, txt);

                break;
            case R.id.input_index_delete:
                if (inputStr.length() != 0) inputStr.deleteCharAt(inputStr.length() - 1);

                setOkBackgroundColor();

                textChangeListener.changeText(inputStr.toString());
                index--;

                break;
            case R.id.checkout_receipt_confirm:
                textChangeListener.confirm();

                break;
        }
    }

    public void setOnTextChangeListener(TextChangeListener textChangeListener) {
        this.textChangeListener = textChangeListener;
    }

    public interface TextChangeListener {
       /* void addText(int index, String sub);

        void delText(int index);*/

        void changeText(String sub);

        void confirm();

    }


    private void setAmount(String number) {
        String before = inputStr.toString().trim();
        if (TextUtils.isEmpty(before)) {//输入框为空
            if (number.equals(".")) {
                inputStr.append("0.");
                return;
            }
            if (number.equals("00")) {
                inputStr.append("0");
                return;
            }
        } else {//输入框不为空
            if (before.equals("0")) {//输入框中为0的情况
                inputStr.setLength(0);
                if (number.equals(".")) {
                    inputStr.append("0.");
                } else if (number.equals("00") || number.equals("0")) {
                    inputStr.append("0");
                } else {
                    inputStr.append(number);
                }
                return;
            }
            if (NumberUtils.isDecimal(before)) {
                if (number.equals(".")) {//已经是小数,不能再输入点
                    return;
                }

                if (NumberUtils.isNDecimal(nDecimal,before)) {//两位小数
                    return;
                }

                if (NumberUtils.isOneDecimal(before) && number.equals("00")) {//输入两个0
                    number = "0";
                }
            }
        }
        inputStr.append(number);
    }

    public void setNDecimal(int n){
        this.nDecimal = n;
        inputStr.delete(0,inputStr.length());
    }

    private void setOkBackgroundColor(){
        if (StrUtils.isNotBlank(inputStr.toString())){
            buttonCifirm.setBackgroundResource(R.drawable.bg_rect_bottom_blue_right);
            buttonCifirm.setTextColor(mContext.getResources().getColor(R.color.white));
        }else {
            buttonCifirm.setBackgroundResource(R.drawable.bg_rect_bottom_right);
            buttonCifirm.setTextColor(mContext.getResources().getColor(R.color.colorTxt));
        }
    }

}
