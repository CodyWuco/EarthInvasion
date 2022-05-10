package com.gamecodeschool.myearthinvasion;

import android.graphics.PointF;
import android.graphics.RectF;

import java.util.ArrayList;

class PhysicsEngine {

    // This signature and much more will change later in the project
    boolean update(long fps, ParticleSystem ps){

        if(ps.mIsRunning){
            ps.update(fps);
        }

        return false;
    }

    // Collision detection will go here

    // Collision detection will go here
    private boolean detectCollisions(GameState mGameState, ArrayList<GameObject> objects, SoundEngine se, ParticleSystem ps ){
        boolean playerHit = false;
        for(GameObject go1 : objects) {

            if(go1.checkActive()){
                // The ist object is active so worth checking

                for(GameObject go2 : objects) {

                    if(go2.checkActive()){

                        // The 2nd object is active so worth checking
                        if(RectF.intersects(go1.getTransform().getCollider(), go2.getTransform().getCollider())){
                            // There has been a collision - but does it matter

                            switch (go1.getTag() + " with " + go2.getTag()){
                                case "Player with Alien Laser":
                                    playerHit = false;
                                    //mGameState.loseLife(se);

                                    break;

                                case "Player with Alien":
                                    playerHit = false;
                                    //mGameState.loseLife(se);

                                    break;

                                case "Player Laser with Alien":
                                    mGameState.increaseScore();
                                    // Respawn the alien
                                    ps.emitParticles(
                                            new PointF(
                                                    go2.getLocation().x,
                                                    go2.getLocation().y

                                            )
                                    );
                                    go2.setInactive();
                                    go2.spawn(objects.get(Level.PLAYER_INDEX).getTransform());
                                    go1.setInactive();
                                    se.playAlienExplode();

                                    break;

                                default:
                                    break;
                            }
                        }
                    }
                }
            }
        }
        return playerHit;
    }
}

