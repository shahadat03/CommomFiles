//XML

        <com.happihub.bd.Utility.BatchView
            android:id="@+id/notificationBatch"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_alignParentTop="true"
            android:layout_marginStart="-10dp"
            android:layout_toEndOf="@id/img"
            android:background="@drawable/notification_batch_background"
            android:fontFamily="@font/open_sans_bold"
            android:gravity="center"
            android:inputType="number"
            android:text="9"
            android:textColor="@color/White"
            android:textSize="@dimen/_10ssp"
            android:visibility="visible" />


//Class
public class BatchView extends TextView {
    public BatchView(Context context) {
        super(context);
    }

    public BatchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BatchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int r = Math.max(getMeasuredWidth(),getMeasuredHeight());
        setMeasuredDimension(r, r);

    }
}
