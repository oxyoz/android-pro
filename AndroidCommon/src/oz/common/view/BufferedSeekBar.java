package oz.common.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

@SuppressLint("ClickableViewAccessibility")
public class BufferedSeekBar extends View {

	private int bgcolor = 0xFFFF00FF;

	private int progressColor = 0xFF009966;

	private int bufferedColor = 0xFFCCCC33;

	private int pointerColor = 0xFFFFFFFF;

	private float pointerSize = 40;

	private float size = 16;

	private  float progressMax = 1000;

	private  float progressMin = 0;

	private final float max = 100;

	private final float min = 0;

	private float progress = 0;

	private float bufferedProgress = 0;

	private OnProgressChange onProgressChange = null;

	public BufferedSeekBar(Context context) {
		super(context);
	}

	public BufferedSeekBar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public BufferedSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {

		super(context, attrs, defStyleAttr);

	}

	private float currentProgress() {

		return getWidth() * progress / progressMax;

	}

	private float currentBufferedProgress() {

		return getWidth() * bufferedProgress / progressMax;

	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {

		canvas.drawARGB(0, 0, 0, 0);

		Paint paint = new Paint();

		paint.setAntiAlias(true);

		paint.setColor(bgcolor);

		canvas.drawRect(0, getHeight() / 2 - size / 2, getWidth(), getHeight()
				/ 2 + size / 2, paint);

		paint.setColor(bufferedColor);

		canvas.drawRect(0, getHeight() / 2 - size / 2,
				currentBufferedProgress(), getHeight() / 2 + size / 2, paint);

		paint.setColor(progressColor);

		canvas.drawRect(0, getHeight() / 2 - size / 2, currentProgress(),
				getHeight() / 2 + size / 2, paint);

		paint.setColor(pointerColor);

		canvas.drawCircle(currentProgress(), getHeight() / 2, pointerSize / 2,
				paint);

		paint.setColor(progressColor);

		canvas.drawCircle(currentProgress(), getHeight() / 2, size / 2, paint);

	}

	private boolean isUser = false;

	@Override
	public boolean onTouchEvent(MotionEvent event) {

			isUser = true;

			progress = progressMax * event.getX() / getWidth();

			invalidate();
			
			if (onProgressChange != null)
				onProgressChange.onProgressChanged(progress, isUser);

			if(event.getAction() == MotionEvent.ACTION_UP) isUser = false;
			
		return false;
	}

	public interface OnProgressChange {

		public void onProgressChanged(float progress, boolean isUser);

	}

	public OnProgressChange getOnProgressChange() {
		return onProgressChange;
	}

	public void setOnProgressChange(OnProgressChange mOnProgressChange) {
		this.onProgressChange = mOnProgressChange;
	}

	public float getPointerSize() {
		return pointerSize;
	}

	public void setPointerSize(float pointerSize) {
		this.pointerSize = pointerSize;
	}

	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}

	public float getBufferedProgress() {
		return bufferedProgress;
	}

	public void setBufferedProgress(float bufferedProgress) {
		this.bufferedProgress = bufferedProgress;
		invalidate();
	}

	public float getProgressMax() {
		return progressMax;
	}

	
	
	public void setProgressMax(float progressMax) {
		this.progressMax = progressMax;
	}

	public void setProgressMin(float progressMin) {
		this.progressMin = progressMin;
	}

	public float getProgressMin() {
		return progressMin;
	}

	public float getMax() {
		return max;
	}

	public float getMin() {
		return min;
	}

	public float getProgress() {
		return progress;
	}

	public void setProgress(float progress) {

		this.progress = progress;

		invalidate();

	}

	public int getProgressColor() {
		return progressColor;
	}

	public void setProgressColor(int progressColor) {
		this.progressColor = progressColor;
	}

	public int getBgcolor() {
		return bgcolor;
	}

	public void setBgcolor(int bgcolor) {
		this.bgcolor = bgcolor;
	}

	public int getBufferedColor() {
		return bufferedColor;
	}

	public void setBufferedColor(int bufferedColor) {
		this.bufferedColor = bufferedColor;
	}

	public int getPointerColor() {
		return pointerColor;
	}

	public void setPointerColor(int pointerColor) {
		this.pointerColor = pointerColor;
	}

	public void setPointerSize(int pointerSize) {
		this.pointerSize = pointerSize;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
