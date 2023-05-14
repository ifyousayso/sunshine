package com.example.sunshine;

//import android.support.annotation.NonNull;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Art extends ImageView {
	private static class ArtBit {
		public final Paint paint = new Paint();
		public final Path path = new Path();

		public ArtBit(int canvasHeight) {
			this.paint.setAntiAlias(true);
			this.paint.setColor(Color.BLACK);
			this.paint.setStyle(Paint.Style.STROKE);
			this.paint.setStrokeCap(Paint.Cap.BUTT);
			this.paint.setStrokeJoin(Paint.Join.ROUND);
			this.paint.setStrokeWidth(canvasHeight / 128.0f);
		}
	}

	public static final float RATIO = 3.0f / 2.0f;
	private int state = -1;
	private int height;
	private final Paint paint = new Paint();
	private final Path path = new Path();
	private final ArrayList<ArtBit> artBits = new ArrayList<>();

	// Purpose: Constructor!
	// Arguments: Context context
	public Art(Context context) {
		super(context);
	}

	// Purpose: Constructor!
	// Arguments: Context context, @Nullable AttributeSet attrs
	public Art(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	// Purpose: Constructor!
	// Arguments: Context context, @Nullable AttributeSet attrs, int defStyleAttr
	public Art(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	// Purpose: When the size of Art changes (at start), set its height and the stroke width.
	// Arguments: int width, int height, int oldWidth, int oldHeight
	// Return: -
	@Override
	protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
		super.onSizeChanged(width, height, oldWidth, oldHeight);

		this.height = Math.round(width / Art.RATIO);
		this.getLayoutParams().height = this.height;
//		this.paint.setStrokeWidth(this.width / 128.0f);
		for (int i = 0; i < this.artBits.size(); i++) {
			this.artBits.get(i).paint.setStrokeWidth(this.height / 128.0f);
		}
	}

	// Use this function to convert a value into the percetange of the canvas height.
	// For the width:height ratio of 3:2 the canvas dimensions would be 150:100.
	private float pH(float amount) {
		return amount * 0.01f * this.height;
	}

	private void addGallows() {
		ArtBit artBit = new ArtBit(this.height);
		artBit.path.moveTo(this.pH(90.0f), this.pH(90.0f));
		artBit.path.lineTo(this.pH(10.0f), this.pH(90.0f));
		artBit.path.moveTo(this.pH(20.0f), this.pH(90.0f));
		artBit.path.lineTo(this.pH(20.0f), this.pH(10.0f));
		artBit.path.lineTo(this.pH(50.0f), this.pH(10.0f));
		this.artBits.add(artBit);
	}

	private void addHead() {
		ArtBit artBit = new ArtBit(this.height);
		artBit.path.addArc(this.pH(64.0f), this.pH(25.0f), this.pH(76.0f), this.pH(37.0f), 0.0f, 360.0f);
		artBit.path.moveTo(this.pH(66.75f), this.pH(28.5f));
		artBit.path.lineTo(this.pH(69.0f), this.pH(30.5f));
		artBit.path.moveTo(this.pH(66.75f), this.pH(30.5f));
		artBit.path.lineTo(this.pH(69.0f), this.pH(28.5f));
		artBit.path.moveTo(this.pH(71.0f), this.pH(28.5f));
		artBit.path.lineTo(this.pH(73.25f), this.pH(30.5f));
		artBit.path.moveTo(this.pH(71.0f), this.pH(30.5f));
		artBit.path.lineTo(this.pH(73.25f), this.pH(28.5f));
		artBit.path.moveTo(this.pH(67.75f), this.pH(33.5f));
		artBit.path.lineTo(this.pH(72.25f), this.pH(33.5f));
		this.artBits.add(artBit);
	}

	private void addTorso() {
		ArtBit artBit = new ArtBit(this.height);
		artBit.path.moveTo(this.pH(70.0f), this.pH(37.0f));
		artBit.path.lineTo(this.pH(70.0f), this.pH(58.0f));
		this.artBits.add(artBit);
	}

	private void addRightLeg() {
		ArtBit artBit = new ArtBit(this.height);
		artBit.path.moveTo(this.pH(60.0f), this.pH(73.0f));
		artBit.path.lineTo(this.pH(70.0f), this.pH(58.0f));
		this.artBits.add(artBit);
	}

	private void addLeftLeg() {
		ArtBit artBit = new ArtBit(this.height);
		artBit.path.moveTo(this.pH(80.0f), this.pH(73.0f));
		artBit.path.lineTo(this.pH(70.0f), this.pH(58.0f));
		this.artBits.add(artBit);
	}

	private void addRightArm() {
		ArtBit artBit = new ArtBit(this.height);
		artBit.path.moveTo(this.pH(60.0f), this.pH(55.0f));
		artBit.path.lineTo(this.pH(70.0f), this.pH(40.0f));
		this.artBits.add(artBit);
	}

	private void addLeftArm() {
		ArtBit artBit = new ArtBit(this.height);
		artBit.path.moveTo(this.pH(80.0f), this.pH(55.0f));
		artBit.path.lineTo(this.pH(70.0f), this.pH(40.0f));
		this.artBits.add(artBit);
	}

	private void addNoose() {
		ArtBit artBit = new ArtBit(this.height);
		artBit.path.moveTo(this.pH(50.0f), this.pH(10.0f));
		artBit.path.lineTo(this.pH(70.0f), this.pH(10.0f));
		artBit.path.lineTo(this.pH(70.0f), this.pH(25.0f));
		this.artBits.add(artBit);
	}

	private void drawSun(float x, float y) {
		ArtBit artBit = new ArtBit(this.height);
		artBit.paint.setColor(Color.WHITE);
		artBit.paint.setStyle(Paint.Style.FILL);
		artBit.path.addCircle(x, y, this.pH(5.0f), Path.Direction.CW);
		this.artBits.add(artBit);

		artBit = new ArtBit(this.height);
		artBit.path.addCircle(x, y, this.pH(5.0f), Path.Direction.CW);
		artBit.path.rMoveTo(this.pH(3.0f), 0.0f);
		artBit.path.rLineTo(this.pH(3.0f), 0.0f);
		artBit.path.moveTo(x, y);
		artBit.path.rMoveTo(this.pH(8.0f / Tools.sqrtTwo), this.pH(-8.0f / Tools.sqrtTwo));
		artBit.path.rLineTo(this.pH(3.0f / Tools.sqrtTwo), this.pH(-3.0f / Tools.sqrtTwo));
		artBit.path.moveTo(x, y);
		artBit.path.rMoveTo(0.0f, this.pH(-8.0f));
		artBit.path.rLineTo(0.0f, this.pH(-3.0f));
		artBit.path.moveTo(x, y);
		artBit.path.rMoveTo(this.pH(-8.0f / Tools.sqrtTwo), this.pH(-8.0f / Tools.sqrtTwo));
		artBit.path.rLineTo(this.pH(-3.0f / Tools.sqrtTwo), this.pH(-3.0f / Tools.sqrtTwo));
		artBit.path.moveTo(x, y);
		artBit.path.rMoveTo(this.pH(-8.0f), 0.0f);
		artBit.path.rLineTo(this.pH(-3.0f), 0.0f);
		artBit.path.moveTo(x, y);
		artBit.path.rMoveTo(this.pH(-8.0f / Tools.sqrtTwo), this.pH(8.0f / Tools.sqrtTwo));
		artBit.path.rLineTo(this.pH(-3.0f / Tools.sqrtTwo), this.pH(3.0f / Tools.sqrtTwo));
		artBit.path.moveTo(x, y);
		artBit.path.rMoveTo(0.0f, this.pH(8.0f));
		artBit.path.rLineTo(0.0f, this.pH(3.0f));
		artBit.path.moveTo(x, y);
		artBit.path.rMoveTo(this.pH(8.0f / Tools.sqrtTwo), this.pH(8.0f / Tools.sqrtTwo));
		artBit.path.rLineTo(this.pH(3.0f / Tools.sqrtTwo), this.pH(3.0f / Tools.sqrtTwo));
		this.artBits.add(artBit);
	}

	private void drawCloud(float x, float y) {
		ArtBit artBit = new ArtBit(this.height);
		artBit.paint.setColor(Color.LTGRAY);
		artBit.paint.setStyle(Paint.Style.FILL_AND_STROKE);
		artBit.path.moveTo(x, y);
//		artBit.path.addRect(x, y, x + this.pH(10.0f), y + this.pH(20.0f), Path.Direction.CW);
//		artBit.path.rCubicTo(this.pH(-4.0f), this.pH(4.0f), this.pH(-8.0f), this.pH(4.0f), this.pH(-12.0f), 0.0f);
//		artBit.path.rCubicTo(this.pH(-12.0f), this.pH(3.0f), this.pH(-12.0f), this.pH(-12.0f), this.pH(-6.0f), this.pH(-12.0f));
//		artBit.path.rCubicTo(this.pH(4.0f), this.pH(-6.0f), this.pH(8.0f), this.pH(-12.0f), this.pH(12.0f), this.pH(-6.0f));

//		artBit.path.arcTo(x - this.pH(30.0f), y, x, y + this.pH(15.0f), 45.0f, 90.0f, true);

		artBit.path.addCircle(x - this.pH(5.0f), y - this.pH(7.0f), this.pH(8.0f), Path.Direction.CW);
		artBit.path.addCircle(x - this.pH(14.0f), y, this.pH(8.0f), Path.Direction.CW);
		artBit.path.addCircle(x - this.pH(5.0f), y + this.pH(5.0f), this.pH(5.0f), Path.Direction.CW);

		artBit.path.addCircle(x + this.pH(1.0f), y + this.pH(3.0f), this.pH(5.0f), Path.Direction.CW);
		artBit.path.addCircle(x + this.pH(8.0f), y + this.pH(1.0f), this.pH(5.0f), Path.Direction.CW);
		artBit.path.addCircle(x + this.pH(4.0f), y - this.pH(4.0f), this.pH(5.0f), Path.Direction.CW);

//		artBit.path.rCubicTo(this.pH(-4.0f), this.pH(4.0f), this.pH(-8.0f), this.pH(4.0f), this.pH(-12.0f), 0.0f);
//		artBit.path.rCubicTo(this.pH(-12.0f), 0.0f, this.pH(-12.0f), this.pH(-12.0f), this.pH(-6.0f), this.pH(-12.0f));

//		float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean forceMoveTo)

		this.artBits.add(artBit);

	}

	private void drawSceneA() {
		this.drawSun(this.pH(125.0f), this.pH(25.0f));
		this.drawCloud(this.pH(50.0f), this.pH(30.0f));
	}

	private void drawSceneB() {
		this.drawSun(this.pH(125.0f), this.pH(25.0f));
		this.drawCloud(this.pH(50.0f), this.pH(30.0f));
		this.drawCloud(this.pH(100.0f), this.pH(40.0f));
	}

	private void drawSceneC() {
		this.drawSun(this.pH(125.0f), this.pH(25.0f));
		this.drawCloud(this.pH(50.0f), this.pH(30.0f));
		this.drawCloud(this.pH(100.0f), this.pH(40.0f));
		this.drawCloud(this.pH(25.0f), this.pH(70.0f));
	}

	private void drawSceneD() {
		this.drawSun(this.pH(125.0f), this.pH(25.0f));
		this.drawCloud(this.pH(50.0f), this.pH(30.0f));
		this.drawCloud(this.pH(100.0f), this.pH(40.0f));
		this.drawCloud(this.pH(25.0f), this.pH(70.0f));
		this.drawCloud(this.pH(40.0f), this.pH(90.0f));
	}

	private void drawSceneE() {
		this.drawSun(this.pH(125.0f), this.pH(25.0f));
		this.drawCloud(this.pH(50.0f), this.pH(30.0f));
		this.drawCloud(this.pH(100.0f), this.pH(40.0f));
		this.drawCloud(this.pH(25.0f), this.pH(70.0f));
		this.drawCloud(this.pH(40.0f), this.pH(90.0f));
		this.drawCloud(this.pH(100.0f), this.pH(60.0f));
	}

	private void drawSceneF() {
		this.drawSun(this.pH(125.0f), this.pH(25.0f));
		this.drawCloud(this.pH(50.0f), this.pH(30.0f));
		this.drawCloud(this.pH(100.0f), this.pH(40.0f));
		this.drawCloud(this.pH(25.0f), this.pH(70.0f));
		this.drawCloud(this.pH(40.0f), this.pH(90.0f));
		this.drawCloud(this.pH(100.0f), this.pH(60.0f));
		this.drawCloud(this.pH(125.0f), this.pH(25.0f));
	}

	private void drawSceneG() {
		this.drawSun(this.pH(125.0f), this.pH(25.0f));
		this.drawCloud(this.pH(50.0f), this.pH(30.0f));
		this.drawCloud(this.pH(100.0f), this.pH(40.0f));
	}

	private void drawSceneH() {
		this.drawSun(this.pH(125.0f), this.pH(25.0f));
		this.drawCloud(this.pH(50.0f), this.pH(30.0f));
		this.drawCloud(this.pH(100.0f), this.pH(40.0f));
	}

	private void drawSceneI() {
		this.drawSun(this.pH(125.0f), this.pH(25.0f));
		this.drawCloud(this.pH(50.0f), this.pH(30.0f));
		this.drawCloud(this.pH(100.0f), this.pH(40.0f));
	}

	private void drawSceneZ() {
		this.drawSun(this.pH(125.0f), this.pH(10.0f));
	}

	// Purpose: Progress the state of the drawing by adding another part.
	// Arguments: -
	// Return: -
	public void progress() {
		this.artBits.clear();

//		this.state++;
//		switch (this.state) {
//			case 0:
//				this.drawSceneA(); break;
//			case 1:
//				this.drawSceneB(); break;
//			case 2:
//				this.drawSceneC(); break;
//			case 3:
//				this.drawSceneD(); break;
//			case 4:
//				this.drawSceneE(); break;
//			case 5:
//				this.drawSceneF(); break;
//			case 6:
//				this.drawSceneG(); break;
//			case 7:
//				this.drawSceneH(); break;
//			case 8:
//				this.drawSceneI(); break;
//			default:
//				this.drawSceneZ(); break;
//		}

		this.drawSun(this.pH(125.0f), this.pH(25.0f));

		this.state++;
		switch (this.state) {
			case 8: this.addNoose();
			case 7: this.addLeftArm();
			case 6: this.addRightArm();
			case 5: this.addLeftLeg();
			case 4: this.addRightLeg();
			case 3: this.addTorso();
			case 2: this.addHead();
			case 1: this.addGallows();
		}

		this.invalidate();
	}

	// Purpose: Draw the hanged man progress.
	// Arguments: Canvas canvas
	// Return: -
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		if (this.state == -1) {
			this.progress();
		}

		canvas.drawColor(Color.WHITE);
		for (int i = 0; i < this.artBits.size(); i++) {
			canvas.drawPath(this.artBits.get(i).path, this.artBits.get(i).paint);
		}
	}
}

