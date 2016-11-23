package com.simon.welcomepage;

import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;

public class WelPageTransformer implements PageTransformer {
	/*
	 * position -1 0 1
	 */
	@Override
	public void transformPage(View page, float position) {
		// View parent = (View) page.getParent();
		// int tag = (int) page.getTag();
		// int colorR = page.getContext().getResources().getColor(R.color.red);
		// int colorG =
		// page.getContext().getResources().getColor(R.color.green);
		// int colorB = page.getContext().getResources().getColor(R.color.blue);
		// ArgbEvaluator evaluator = new ArgbEvaluator();
		// int color = colorR;
		// Log.i("Simon", "-----------------" + tag);
		// switch (tag) {
		// case 0:
		// color = (int) evaluator
		// .evaluate(Math.abs(position), colorR, colorG);
		// break;
		// case 1:
		// color = (int) evaluator
		// .evaluate(Math.abs(position), colorR, colorR);
		// break;
		// case 2:
		// color = (int) evaluator
		// .evaluate(Math.abs(position), colorR, colorR);
		// break;
		// default:
		// break;
		// }
		// parent.setBackgroundColor(color);
	}
}
