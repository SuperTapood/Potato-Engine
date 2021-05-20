package potato.render;

import potato.Core;

import java.util.ArrayList;
import java.util.List;

public class Renderer extends Core {
    public final List<RenderBatch> batches;
    public final int MAX_BATCH_SIZE = 1000;

    public Renderer() {
        this.batches = new ArrayList<>();
    }

    public void add(Potato sprite) {
        boolean added = false;
        for (RenderBatch batch : batches) {
            if (batch.hasRoom()) {
                Texture tex = sprite.getTexture();
                if (tex == null || (batch.hasTexture(tex) || batch.hasTextureRoom())) {
                    batch.addSprite(sprite);
                    added = true;
                    break;
                }
            }
        }

        if (!added) {
            RenderBatch newBatch = new RenderBatch(MAX_BATCH_SIZE);
            newBatch.start();
            batches.add(newBatch);
            newBatch.addSprite(sprite);
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
