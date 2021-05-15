package potato.render;

import potato.Window;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Renderer {
    private final List<RenderBatch> batches;
    private final int MAX_BATCH_SIZE = 1000;

    public Renderer() {
        this.batches = new ArrayList<>();
    }

    public void add(Potato sprite) {
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
            RenderBatch newBatch = new RenderBatch(MAX_BATCH_SIZE, sprite.zIndex);
            newBatch.start();
            batches.add(newBatch);
            newBatch.addSprite(sprite);
            Collections.sort(batches);
        }
    }

    public void renderAll() {
        for (RenderBatch batch : batches) {
            batch.render();
        }
    }

    public void render(int index) {
        int batchIndex = index / MAX_BATCH_SIZE;
        batches.get(batchIndex).render(index % 1000);
    }
}
