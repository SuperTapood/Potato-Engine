package potato.render;

import potato.Window;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Renderer {
    private final List<RenderBatch> batches;

    public Renderer() {
        this.batches = new ArrayList<>();
    }

    public void add(SpriteRenderer sprite, Window window) {
        boolean added = false;
        for (RenderBatch batch : batches) {
            if (batch.hasRoom() && batch.zIndex() == sprite.zIndex) {
                Texture tex = sprite.getTexture();
                if (tex == null || (batch.hasTexture(tex) || batch.hasTextureRoom())) {
                    batch.addSprite(sprite);
                    added = true;
                    break;
                }
            }
        }

        if (!added) {
            int MAX_BATCH_SIZE = 1000;
            RenderBatch newBatch = new RenderBatch(MAX_BATCH_SIZE, sprite.zIndex, window);
            newBatch.start();
            batches.add(newBatch);
            newBatch.addSprite(sprite);
            Collections.sort(batches);
        }
    }

    public void render(Window window) {
        for (RenderBatch batch : batches) {
            batch.render(window);
        }
    }
}
