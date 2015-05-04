package storm.blueprint.Chapter3.trident.state;

import storm.trident.state.map.NonTransactionalMap;

public class OutbreakTrendState extends NonTransactionalMap<Long> {
protected OutbreakTrendState(
OutbreakTrendBackingMap outbreakBackingMap) {
super(outbreakBackingMap);
}
}