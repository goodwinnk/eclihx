/**
 * <copyright>
 * </copyright>
 *
 */
package eclihx.hxml.hxml;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see eclihx.hxml.hxml.HxmlPackage
 * @generated
 */
public interface HxmlFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  HxmlFactory eINSTANCE = eclihx.hxml.hxml.impl.HxmlFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Model</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Model</em>'.
   * @generated
   */
  Model createModel();

  /**
   * Returns a new object of class '<em>Classpath</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Classpath</em>'.
   * @generated
   */
  Classpath createClasspath();

  /**
   * Returns a new object of class '<em>Javascript</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Javascript</em>'.
   * @generated
   */
  Javascript createJavascript();

  /**
   * Returns a new object of class '<em>Swf</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Swf</em>'.
   * @generated
   */
  Swf createSwf();

  /**
   * Returns a new object of class '<em>Swf9</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Swf9</em>'.
   * @generated
   */
  Swf9 createSwf9();

  /**
   * Returns a new object of class '<em>Neko</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Neko</em>'.
   * @generated
   */
  Neko createNeko();

  /**
   * Returns a new object of class '<em>Php</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Php</em>'.
   * @generated
   */
  Php createPhp();

  /**
   * Returns a new object of class '<em>Cpp</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Cpp</em>'.
   * @generated
   */
  Cpp createCpp();

  /**
   * Returns a new object of class '<em>Xml</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Xml</em>'.
   * @generated
   */
  Xml createXml();

  /**
   * Returns a new object of class '<em>Main</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Main</em>'.
   * @generated
   */
  Main createMain();

  /**
   * Returns a new object of class '<em>Lib</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Lib</em>'.
   * @generated
   */
  Lib createLib();

  /**
   * Returns a new object of class '<em>Flag</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Flag</em>'.
   * @generated
   */
  Flag createFlag();

  /**
   * Returns a new object of class '<em>Verbose</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Verbose</em>'.
   * @generated
   */
  Verbose createVerbose();

  /**
   * Returns a new object of class '<em>Debug</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Debug</em>'.
   * @generated
   */
  Debug createDebug();

  /**
   * Returns a new object of class '<em>Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Type</em>'.
   * @generated
   */
  Type createType();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  HxmlPackage getHxmlPackage();

} //HxmlFactory
