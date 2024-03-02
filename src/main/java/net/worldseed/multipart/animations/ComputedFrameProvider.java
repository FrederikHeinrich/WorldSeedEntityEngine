package net.worldseed.multipart.animations;

import net.minestom.server.coordinate.Point;
import net.minestom.server.coordinate.Vec;
import net.worldseed.multipart.ModelLoader;

import java.util.LinkedHashMap;

public class ComputedFrameProvider implements FrameProvider {
    private final LinkedHashMap<Double, BoneAnimationImpl.PointInterpolation> transform;
    private final ModelLoader.AnimationType type;
    private final Double lowestKey;

    public ComputedFrameProvider(LinkedHashMap<Double, BoneAnimationImpl.PointInterpolation> transform, ModelLoader.AnimationType type, int length) {
        this.transform = transform;
        this.type = type;
        this.lowestKey = transform.keySet().stream().min(Double::compareTo).orElse(0.0);
    }

    @Override
    public Point getFrame(int tick) {
        var first = transform.get(lowestKey);
        if (first == null) return Vec.ZERO;

        double toInterpolate = tick * 50.0 / 1000;
        var point = first.p().evaluate(toInterpolate);

        if (type == ModelLoader.AnimationType.ROTATION) return point.mul(RotationMul);
        return point.mul(TranslationMul).mul(0.25);
    }
}