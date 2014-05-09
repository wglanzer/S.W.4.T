package de.swat.clientserverintercom;

/**
 * De- und Encoder
 *
 * @author W.Glanzer, 10.05.2014.
 */
public interface IEncoder
{

  /**
   * Enkodierungsmöglichkeit
   *
   * @param pToEncode T, das enkodiert werden soll
   * @return enkodiertes T
   */
  String encode(String pToEncode);

  /**
   * Dekodierungsmöglichkeit
   *
   * @param pToDecode T, das dekodiert werden soll
   * @return dekodiertes T
   */
  String decode(String pToDecode);

}
