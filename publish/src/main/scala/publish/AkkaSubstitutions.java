package publish;

import com.oracle.svm.core.annotate.Alias;
import com.oracle.svm.core.annotate.RecomputeFieldValue;
import com.oracle.svm.core.annotate.TargetClass;
import akka.actor.LightArrayRevolverScheduler;
import org.agrona.concurrent.status.UnsafeBufferStatusIndicator;
// see https://medium.com/graalvm/instant-netty-startup-using-graalvm-native-image-generation-ed6f14ff7692

@TargetClass(className = "akka.actor.LightArrayRevolverScheduler$")
final class Target_akka_actor_LightArrayRevolverScheduler$ {
    @Alias
    @RecomputeFieldValue(kind = RecomputeFieldValue.Kind.FieldOffset, declClassName = "akka.actor.LightArrayRevolverScheduler$TaskHolder", name = "task")
    public long akka$actor$LightArrayRevolverScheduler$$taskOffset;
}

@TargetClass(io.aeron.driver.Configuration.class)
final class Target_io_aeron_driver_Configuration {
    @Alias
    @RecomputeFieldValue(kind = RecomputeFieldValue.Kind.ArrayIndexScale, declClass= org.agrona.concurrent.status.UnsafeBufferStatusIndicator.class, name="byteArray")
    public long org$agrona$concurrent$status$UnsafeBufferStatusIndicator$$byteArray;
}


@TargetClass(org.agrona.concurrent.AbstractConcurrentArrayQueue.class)
final class Target_org_agrona_concurrent_AbstractConcurrentArrayQueue{
    @Alias
    @RecomputeFieldValue(kind = RecomputeFieldValue.Kind.ArrayIndexScale, declClass= org.agrona.concurrent.status.UnsafeBufferStatusIndicator.class, name="byteArray")
    public long org$agrona$concurrent$status$UnsafeBufferStatusIndicator$$byteArray;
}

public class AkkaSubstitutions {
}
