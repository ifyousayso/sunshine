package com.example.hangman;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class Art extends ImageView {
	private int state = 0;
	private int width;
	private int height;
	private Paint paint = new Paint();
	private Path path = new Path();

	// Purpose: Constructor!
	// Arguments: Context context
	public Art(Context context) {
		super(context);
		this.init();
	}

	// Purpose: Constructor!
	// Arguments: Context context, @Nullable AttributeSet attrs
	public Art(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		this.init();
	}

	// Purpose: Constructor!
	// Arguments: Context context, @Nullable AttributeSet attrs, int defStyleAttr
	public Art(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.init();
	}

	private void init() {
		this.paint.setAntiAlias(true);
		this.paint.setColor(0xff000000); // Black
		this.paint.setStyle(Paint.Style.STROKE);
		this.paint.setStrokeCap(Paint.Cap.BUTT);
		this.paint.setStrokeJoin(Paint.Join.ROUND);
	}

	// Purpose: When the size of Art changes (at start), set its height and the stroke width.
	// Arguments: int width, int height, int oldWidth, int oldHeight
	// Return: -
	@Override
	protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
		super.onSizeChanged(width, height, oldWidth, oldHeight);

		this.width = width;
		this.height = this.width;
		this.getLayoutParams().height = this.height;
		this.paint.setStrokeWidth(this.width / 128.0f);
	}

	private float pW(float amount) {
		return amount * 0.01f * this.width;
	}

	private float pH(float amount) {
		return amount * 0.01f * this.height;
	}

	private void addGallows() {
		this.path.moveTo(this.pW(90.0f), this.pH(90.0f));
		this.path.lineTo(this.pW(10.0f), this.pH(90.0f));
		this.path.moveTo(this.pW(20.0f), this.pH(90.0f));
		this.path.lineTo(this.pW(20.0f), this.pH(10.0f));
		this.path.lineTo(this.pW(50.0f), this.pH(10.0f));
	}

	private void addHead() {
		this.path.addArc(this.pW(64.0f), this.pH(25.0f), this.pW(76.0f), this.pH(37.0f), 0.0f, 360.0f);
		this.path.moveTo(this.pW(66.75f), this.pH(28.5f));
		this.path.lineTo(this.pW(69.0f), this.pH(30.5f));
		this.path.moveTo(this.pW(66.75f), this.pH(30.5f));
		this.path.lineTo(this.pW(69.0f), this.pH(28.5f));
		this.path.moveTo(this.pW(71.0f), this.pH(28.5f));
		this.path.lineTo(this.pW(73.25f), this.pH(30.5f));
		this.path.moveTo(this.pW(71.0f), this.pH(30.5f));
		this.path.lineTo(this.pW(73.25f), this.pH(28.5f));
		this.path.moveTo(this.pW(67.75f), this.pH(33.5f));
		this.path.lineTo(this.pW(72.25f), this.pH(33.5f));
	}

	private void addTorso() {
		this.path.moveTo(this.pW(70.0f), this.pH(37.0f));
		this.path.lineTo(this.pW(70.0f), this.pH(58.0f));
	}

	private void addRightLeg() {
		this.path.moveTo(this.pW(60.0f), this.pH(73.0f));
		this.path.lineTo(this.pW(70.0f), this.pH(58.0f));
	}

	private void addLeftLeg() {
		this.path.moveTo(this.pW(80.0f), this.pH(73.0f));
		this.path.lineTo(this.pW(70.0f), this.pH(58.0f));
	}

	private void addRightArm() {
		this.path.moveTo(this.pW(60.0f), this.pH(55.0f));
		this.path.lineTo(this.pW(70.0f), this.pH(40.0f));
	}

	private void addLeftArm() {
		this.path.moveTo(this.pW(80.0f), this.pH(55.0f));
		this.path.lineTo(this.pW(70.0f), this.pH(40.0f));
	}

	private void addNoose() {
		this.path.moveTo(this.pW(50.0f), this.pH(10.0f));
		this.path.lineTo(this.pW(70.0f), this.pH(10.0f));
		this.path.lineTo(this.pW(70.0f), this.pH(25.0f));
	}

	// Purpose: Progress the state of the drawing by adding another part.
	// Arguments: -
	// Return: -
	public void progress() {
		switch (this.state) {
			case 0:
				this.addGallows(); break;
			case 1:
				this.addHead(); break;
			case 2:
				this.addTorso(); break;
			case 3:
				this.addRightLeg(); break;
			case 4:
				this.addLeftLeg(); break;
			case 5:
				this.addRightArm(); break;
			case 6:
				this.addLeftArm(); break;
			case 7:
				this.addNoose(); break;
			default:
				return;
		}
		this.state++;

		this.invalidate();
	}

	// Purpose: Draw the hanged man progress.
	// Arguments: Canvas canvas
	// Return: -
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawARGB(0xff, 0xff, 0xff, 0xff);
		canvas.drawPath(this.path, this.paint);
	}
}

