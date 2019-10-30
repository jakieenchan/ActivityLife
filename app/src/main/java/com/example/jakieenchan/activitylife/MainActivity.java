package com.example.jakieenchan.activitylife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    //private Button mPrevButton;
    private TextView mQuestionTextView;

    private static  final String TAG = "MainActivity";
    private static final String KEY_INDEX="index";


    private TrueFalse[] mQuestionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, false),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, true),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, true),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
    };

    private int mCurrentIndex = 0 ;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG,"onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX,mCurrentIndex);
    }

    //接收用户点击了哪个按钮，并判断回答是否正确，反馈答题结果
    private void checkAnswer(boolean userPressedTrue){
        boolean answerTrue = mQuestionBank[mCurrentIndex].ismTrueQuestion();

        int messageResId = 0 ;
        if(userPressedTrue == answerTrue){
            messageResId = R.string.correct_toast;
        }else{
            messageResId = R.string.incorrect_toast;
        }

        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();
    }

    //更新题目
    private void updateQuestion(){
        int quesetion = mQuestionBank[mCurrentIndex].getmQuestion();
        mQuestionTextView.setText(quesetion);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //引用TextView，将文本内容设置为当前数组索引执行的问题
        mQuestionTextView = (TextView)findViewById(R.id.question_text_view) ;

        //引用指定按钮
        mTrueButton = (Button)findViewById(R.id.true_button);//接收组件的资源ID作为参数，返回一个视图对象.必须将返回的View转为Button
        //设置监听器
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建提示消息（Toast），
                //参数： Activity实例 + 字符串资源ID + 显示持续时间
                checkAnswer(true);
            }
        });

        mFalseButton = (Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

//        //引用prev按钮
//        mPrevButton = (Button)findViewById(R.id.prev_button);
//        //实现跳转上一题
//        mPrevButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(mCurrentIndex<=1){
//                    mCurrentIndex = (mQuestionBank.length-1) % mQuestionBank.length;
//                }else{
//                    mCurrentIndex = (mCurrentIndex - 1 ) % mQuestionBank.length;
//                }
//                updateQuestion();
//            }
//        });

        //引用next按钮
        mNextButton = (Button) findViewById(R.id.next_button);
        //点击跳转下一题
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1 ) % mQuestionBank.length;
                updateQuestion();
            }
        });

        //为文本设置监听器,实现点击文本就跳转下一题的功能
        mQuestionTextView = findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1 ) % mQuestionBank.length;
                updateQuestion();
            }
        });

        //检查存储的index信息
        if (savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX,0);
        }
        updateQuestion();
    }

    //启动一个activty，处于停止状态，不可见
    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG,"onStart() called");
    }

    //离开前台，暂停activity，用户可见，
    @Override
    public  void onPause(){
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    //运行activity，在前台，可见
    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG,"onResume() called");
    }

    //从暂停变成停止，从可见变成不可见
    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG,"onStop() called");
    }

    //完成/销毁,activity不存在
    @Override
    public  void  onDestroy(){
        super.onDestroy();
        Log.d(TAG,"onDestroy() called");
    }
}
