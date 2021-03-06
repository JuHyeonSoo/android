package com.msi.manning.chapter9.bounceyBall;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.view.View;

public class BounceView extends View {

     /* Our Ball together with the location it will be painted*/
     protected Drawable mySprite;
     protected Point mySpritePos = new Point(0,0);
     
     /* Working with a Enum is 10000%
      * safer than working with int's
      * to 'remember' the direction. */
     protected enum HorizontalDirection {LEFT, RIGHT}
     protected enum VerticalDirection {UP, DOWN}
     protected HorizontalDirection myXDirection = HorizontalDirection.RIGHT;
     protected VerticalDirection myYDirection = VerticalDirection.UP;


     public BounceView(Context context) {
          super(context);
          // Set the background
          this.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.android));
          // Load our "Ball"
          this.mySprite = this.getResources().getDrawable(R.drawable.world);
     }

     @Override
     protected void onDraw(Canvas canvas) {
    	 
         this.mySprite.setBounds(this.mySpritePos.x, this.mySpritePos.y,
                 this.mySpritePos.x + 50, this.mySpritePos.y + 50);
         
          /* Check if the Ball started to leave
           * the screen on left or right side */
          if (mySpritePos.x >= this.getWidth() - mySprite.getBounds().width()) {
               this.myXDirection = HorizontalDirection.LEFT;
          } else if (mySpritePos.x <= 0) {
               this.myXDirection = HorizontalDirection.RIGHT;
          }

          /* Check if the Ball started to leave
           * the screen on bottom or upper side */
          if (mySpritePos.y >= this.getHeight() - mySprite.getBounds().height()) {
               this.myYDirection = VerticalDirection.UP;
          } else if (mySpritePos.y <= 0) {
               this.myYDirection = VerticalDirection.DOWN;
          }

          /* Move the ball left or right */
          if (this.myXDirection == HorizontalDirection.RIGHT) {
               this.mySpritePos.x += 10;
          } else {
               this.mySpritePos.x -= 10;
          }
          /* Move the ball up or down */
          if (this.myYDirection == VerticalDirection.DOWN) {
               this.mySpritePos.y += 10;
          } else {
               this.mySpritePos.y -= 10;
          }

          /* Set the location, where the sprite
           * will draw itself to the canvas */
     
          
          /* Make the sprite draw itself to the canvas */
          this.mySprite.draw(canvas);
     }
}