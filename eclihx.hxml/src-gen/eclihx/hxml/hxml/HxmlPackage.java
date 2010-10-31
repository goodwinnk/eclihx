/**
 * <copyright>
 * </copyright>
 *
 */
package eclihx.hxml.hxml;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see eclihx.hxml.hxml.HxmlFactory
 * @model kind="package"
 * @generated
 */
public interface HxmlPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "hxml";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.xtext.org/example/HXml";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "hxml";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  HxmlPackage eINSTANCE = eclihx.hxml.hxml.impl.HxmlPackageImpl.init();

  /**
   * The meta object id for the '{@link eclihx.hxml.hxml.impl.ModelImpl <em>Model</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eclihx.hxml.hxml.impl.ModelImpl
   * @see eclihx.hxml.hxml.impl.HxmlPackageImpl#getModel()
   * @generated
   */
  int MODEL = 0;

  /**
   * The feature id for the '<em><b>Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL__ELEMENTS = 0;

  /**
   * The number of structural features of the '<em>Model</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link eclihx.hxml.hxml.impl.TypeImpl <em>Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eclihx.hxml.hxml.impl.TypeImpl
   * @see eclihx.hxml.hxml.impl.HxmlPackageImpl#getType()
   * @generated
   */
  int TYPE = 14;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE__VALUE = 0;

  /**
   * The number of structural features of the '<em>Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link eclihx.hxml.hxml.impl.ClasspathImpl <em>Classpath</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eclihx.hxml.hxml.impl.ClasspathImpl
   * @see eclihx.hxml.hxml.impl.HxmlPackageImpl#getClasspath()
   * @generated
   */
  int CLASSPATH = 1;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASSPATH__VALUE = TYPE__VALUE;

  /**
   * The number of structural features of the '<em>Classpath</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASSPATH_FEATURE_COUNT = TYPE_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link eclihx.hxml.hxml.impl.JavascriptImpl <em>Javascript</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eclihx.hxml.hxml.impl.JavascriptImpl
   * @see eclihx.hxml.hxml.impl.HxmlPackageImpl#getJavascript()
   * @generated
   */
  int JAVASCRIPT = 2;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JAVASCRIPT__VALUE = TYPE__VALUE;

  /**
   * The number of structural features of the '<em>Javascript</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JAVASCRIPT_FEATURE_COUNT = TYPE_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link eclihx.hxml.hxml.impl.SwfImpl <em>Swf</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eclihx.hxml.hxml.impl.SwfImpl
   * @see eclihx.hxml.hxml.impl.HxmlPackageImpl#getSwf()
   * @generated
   */
  int SWF = 3;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SWF__VALUE = TYPE__VALUE;

  /**
   * The number of structural features of the '<em>Swf</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SWF_FEATURE_COUNT = TYPE_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link eclihx.hxml.hxml.impl.Swf9Impl <em>Swf9</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eclihx.hxml.hxml.impl.Swf9Impl
   * @see eclihx.hxml.hxml.impl.HxmlPackageImpl#getSwf9()
   * @generated
   */
  int SWF9 = 4;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SWF9__VALUE = TYPE__VALUE;

  /**
   * The number of structural features of the '<em>Swf9</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SWF9_FEATURE_COUNT = TYPE_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link eclihx.hxml.hxml.impl.NekoImpl <em>Neko</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eclihx.hxml.hxml.impl.NekoImpl
   * @see eclihx.hxml.hxml.impl.HxmlPackageImpl#getNeko()
   * @generated
   */
  int NEKO = 5;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NEKO__VALUE = TYPE__VALUE;

  /**
   * The number of structural features of the '<em>Neko</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NEKO_FEATURE_COUNT = TYPE_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link eclihx.hxml.hxml.impl.PhpImpl <em>Php</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eclihx.hxml.hxml.impl.PhpImpl
   * @see eclihx.hxml.hxml.impl.HxmlPackageImpl#getPhp()
   * @generated
   */
  int PHP = 6;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PHP__VALUE = TYPE__VALUE;

  /**
   * The number of structural features of the '<em>Php</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PHP_FEATURE_COUNT = TYPE_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link eclihx.hxml.hxml.impl.CppImpl <em>Cpp</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eclihx.hxml.hxml.impl.CppImpl
   * @see eclihx.hxml.hxml.impl.HxmlPackageImpl#getCpp()
   * @generated
   */
  int CPP = 7;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CPP__VALUE = TYPE__VALUE;

  /**
   * The number of structural features of the '<em>Cpp</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CPP_FEATURE_COUNT = TYPE_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link eclihx.hxml.hxml.impl.XmlImpl <em>Xml</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eclihx.hxml.hxml.impl.XmlImpl
   * @see eclihx.hxml.hxml.impl.HxmlPackageImpl#getXml()
   * @generated
   */
  int XML = 8;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XML__VALUE = TYPE__VALUE;

  /**
   * The number of structural features of the '<em>Xml</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XML_FEATURE_COUNT = TYPE_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link eclihx.hxml.hxml.impl.MainImpl <em>Main</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eclihx.hxml.hxml.impl.MainImpl
   * @see eclihx.hxml.hxml.impl.HxmlPackageImpl#getMain()
   * @generated
   */
  int MAIN = 9;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAIN__VALUE = TYPE__VALUE;

  /**
   * The number of structural features of the '<em>Main</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAIN_FEATURE_COUNT = TYPE_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link eclihx.hxml.hxml.impl.LibImpl <em>Lib</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eclihx.hxml.hxml.impl.LibImpl
   * @see eclihx.hxml.hxml.impl.HxmlPackageImpl#getLib()
   * @generated
   */
  int LIB = 10;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIB__VALUE = TYPE__VALUE;

  /**
   * The number of structural features of the '<em>Lib</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIB_FEATURE_COUNT = TYPE_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link eclihx.hxml.hxml.impl.FlagImpl <em>Flag</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eclihx.hxml.hxml.impl.FlagImpl
   * @see eclihx.hxml.hxml.impl.HxmlPackageImpl#getFlag()
   * @generated
   */
  int FLAG = 11;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FLAG__VALUE = TYPE__VALUE;

  /**
   * The number of structural features of the '<em>Flag</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FLAG_FEATURE_COUNT = TYPE_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link eclihx.hxml.hxml.impl.VerboseImpl <em>Verbose</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eclihx.hxml.hxml.impl.VerboseImpl
   * @see eclihx.hxml.hxml.impl.HxmlPackageImpl#getVerbose()
   * @generated
   */
  int VERBOSE = 12;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VERBOSE__VALUE = TYPE__VALUE;

  /**
   * The number of structural features of the '<em>Verbose</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VERBOSE_FEATURE_COUNT = TYPE_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link eclihx.hxml.hxml.impl.DebugImpl <em>Debug</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eclihx.hxml.hxml.impl.DebugImpl
   * @see eclihx.hxml.hxml.impl.HxmlPackageImpl#getDebug()
   * @generated
   */
  int DEBUG = 13;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DEBUG__VALUE = TYPE__VALUE;

  /**
   * The number of structural features of the '<em>Debug</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DEBUG_FEATURE_COUNT = TYPE_FEATURE_COUNT + 0;


  /**
   * Returns the meta object for class '{@link eclihx.hxml.hxml.Model <em>Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Model</em>'.
   * @see eclihx.hxml.hxml.Model
   * @generated
   */
  EClass getModel();

  /**
   * Returns the meta object for the containment reference list '{@link eclihx.hxml.hxml.Model#getElements <em>Elements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Elements</em>'.
   * @see eclihx.hxml.hxml.Model#getElements()
   * @see #getModel()
   * @generated
   */
  EReference getModel_Elements();

  /**
   * Returns the meta object for class '{@link eclihx.hxml.hxml.Classpath <em>Classpath</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Classpath</em>'.
   * @see eclihx.hxml.hxml.Classpath
   * @generated
   */
  EClass getClasspath();

  /**
   * Returns the meta object for class '{@link eclihx.hxml.hxml.Javascript <em>Javascript</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Javascript</em>'.
   * @see eclihx.hxml.hxml.Javascript
   * @generated
   */
  EClass getJavascript();

  /**
   * Returns the meta object for class '{@link eclihx.hxml.hxml.Swf <em>Swf</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Swf</em>'.
   * @see eclihx.hxml.hxml.Swf
   * @generated
   */
  EClass getSwf();

  /**
   * Returns the meta object for class '{@link eclihx.hxml.hxml.Swf9 <em>Swf9</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Swf9</em>'.
   * @see eclihx.hxml.hxml.Swf9
   * @generated
   */
  EClass getSwf9();

  /**
   * Returns the meta object for class '{@link eclihx.hxml.hxml.Neko <em>Neko</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Neko</em>'.
   * @see eclihx.hxml.hxml.Neko
   * @generated
   */
  EClass getNeko();

  /**
   * Returns the meta object for class '{@link eclihx.hxml.hxml.Php <em>Php</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Php</em>'.
   * @see eclihx.hxml.hxml.Php
   * @generated
   */
  EClass getPhp();

  /**
   * Returns the meta object for class '{@link eclihx.hxml.hxml.Cpp <em>Cpp</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Cpp</em>'.
   * @see eclihx.hxml.hxml.Cpp
   * @generated
   */
  EClass getCpp();

  /**
   * Returns the meta object for class '{@link eclihx.hxml.hxml.Xml <em>Xml</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Xml</em>'.
   * @see eclihx.hxml.hxml.Xml
   * @generated
   */
  EClass getXml();

  /**
   * Returns the meta object for class '{@link eclihx.hxml.hxml.Main <em>Main</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Main</em>'.
   * @see eclihx.hxml.hxml.Main
   * @generated
   */
  EClass getMain();

  /**
   * Returns the meta object for class '{@link eclihx.hxml.hxml.Lib <em>Lib</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Lib</em>'.
   * @see eclihx.hxml.hxml.Lib
   * @generated
   */
  EClass getLib();

  /**
   * Returns the meta object for class '{@link eclihx.hxml.hxml.Flag <em>Flag</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Flag</em>'.
   * @see eclihx.hxml.hxml.Flag
   * @generated
   */
  EClass getFlag();

  /**
   * Returns the meta object for class '{@link eclihx.hxml.hxml.Verbose <em>Verbose</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Verbose</em>'.
   * @see eclihx.hxml.hxml.Verbose
   * @generated
   */
  EClass getVerbose();

  /**
   * Returns the meta object for class '{@link eclihx.hxml.hxml.Debug <em>Debug</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Debug</em>'.
   * @see eclihx.hxml.hxml.Debug
   * @generated
   */
  EClass getDebug();

  /**
   * Returns the meta object for class '{@link eclihx.hxml.hxml.Type <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Type</em>'.
   * @see eclihx.hxml.hxml.Type
   * @generated
   */
  EClass getType();

  /**
   * Returns the meta object for the attribute '{@link eclihx.hxml.hxml.Type#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see eclihx.hxml.hxml.Type#getValue()
   * @see #getType()
   * @generated
   */
  EAttribute getType_Value();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  HxmlFactory getHxmlFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link eclihx.hxml.hxml.impl.ModelImpl <em>Model</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see eclihx.hxml.hxml.impl.ModelImpl
     * @see eclihx.hxml.hxml.impl.HxmlPackageImpl#getModel()
     * @generated
     */
    EClass MODEL = eINSTANCE.getModel();

    /**
     * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL__ELEMENTS = eINSTANCE.getModel_Elements();

    /**
     * The meta object literal for the '{@link eclihx.hxml.hxml.impl.ClasspathImpl <em>Classpath</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see eclihx.hxml.hxml.impl.ClasspathImpl
     * @see eclihx.hxml.hxml.impl.HxmlPackageImpl#getClasspath()
     * @generated
     */
    EClass CLASSPATH = eINSTANCE.getClasspath();

    /**
     * The meta object literal for the '{@link eclihx.hxml.hxml.impl.JavascriptImpl <em>Javascript</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see eclihx.hxml.hxml.impl.JavascriptImpl
     * @see eclihx.hxml.hxml.impl.HxmlPackageImpl#getJavascript()
     * @generated
     */
    EClass JAVASCRIPT = eINSTANCE.getJavascript();

    /**
     * The meta object literal for the '{@link eclihx.hxml.hxml.impl.SwfImpl <em>Swf</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see eclihx.hxml.hxml.impl.SwfImpl
     * @see eclihx.hxml.hxml.impl.HxmlPackageImpl#getSwf()
     * @generated
     */
    EClass SWF = eINSTANCE.getSwf();

    /**
     * The meta object literal for the '{@link eclihx.hxml.hxml.impl.Swf9Impl <em>Swf9</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see eclihx.hxml.hxml.impl.Swf9Impl
     * @see eclihx.hxml.hxml.impl.HxmlPackageImpl#getSwf9()
     * @generated
     */
    EClass SWF9 = eINSTANCE.getSwf9();

    /**
     * The meta object literal for the '{@link eclihx.hxml.hxml.impl.NekoImpl <em>Neko</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see eclihx.hxml.hxml.impl.NekoImpl
     * @see eclihx.hxml.hxml.impl.HxmlPackageImpl#getNeko()
     * @generated
     */
    EClass NEKO = eINSTANCE.getNeko();

    /**
     * The meta object literal for the '{@link eclihx.hxml.hxml.impl.PhpImpl <em>Php</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see eclihx.hxml.hxml.impl.PhpImpl
     * @see eclihx.hxml.hxml.impl.HxmlPackageImpl#getPhp()
     * @generated
     */
    EClass PHP = eINSTANCE.getPhp();

    /**
     * The meta object literal for the '{@link eclihx.hxml.hxml.impl.CppImpl <em>Cpp</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see eclihx.hxml.hxml.impl.CppImpl
     * @see eclihx.hxml.hxml.impl.HxmlPackageImpl#getCpp()
     * @generated
     */
    EClass CPP = eINSTANCE.getCpp();

    /**
     * The meta object literal for the '{@link eclihx.hxml.hxml.impl.XmlImpl <em>Xml</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see eclihx.hxml.hxml.impl.XmlImpl
     * @see eclihx.hxml.hxml.impl.HxmlPackageImpl#getXml()
     * @generated
     */
    EClass XML = eINSTANCE.getXml();

    /**
     * The meta object literal for the '{@link eclihx.hxml.hxml.impl.MainImpl <em>Main</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see eclihx.hxml.hxml.impl.MainImpl
     * @see eclihx.hxml.hxml.impl.HxmlPackageImpl#getMain()
     * @generated
     */
    EClass MAIN = eINSTANCE.getMain();

    /**
     * The meta object literal for the '{@link eclihx.hxml.hxml.impl.LibImpl <em>Lib</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see eclihx.hxml.hxml.impl.LibImpl
     * @see eclihx.hxml.hxml.impl.HxmlPackageImpl#getLib()
     * @generated
     */
    EClass LIB = eINSTANCE.getLib();

    /**
     * The meta object literal for the '{@link eclihx.hxml.hxml.impl.FlagImpl <em>Flag</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see eclihx.hxml.hxml.impl.FlagImpl
     * @see eclihx.hxml.hxml.impl.HxmlPackageImpl#getFlag()
     * @generated
     */
    EClass FLAG = eINSTANCE.getFlag();

    /**
     * The meta object literal for the '{@link eclihx.hxml.hxml.impl.VerboseImpl <em>Verbose</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see eclihx.hxml.hxml.impl.VerboseImpl
     * @see eclihx.hxml.hxml.impl.HxmlPackageImpl#getVerbose()
     * @generated
     */
    EClass VERBOSE = eINSTANCE.getVerbose();

    /**
     * The meta object literal for the '{@link eclihx.hxml.hxml.impl.DebugImpl <em>Debug</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see eclihx.hxml.hxml.impl.DebugImpl
     * @see eclihx.hxml.hxml.impl.HxmlPackageImpl#getDebug()
     * @generated
     */
    EClass DEBUG = eINSTANCE.getDebug();

    /**
     * The meta object literal for the '{@link eclihx.hxml.hxml.impl.TypeImpl <em>Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see eclihx.hxml.hxml.impl.TypeImpl
     * @see eclihx.hxml.hxml.impl.HxmlPackageImpl#getType()
     * @generated
     */
    EClass TYPE = eINSTANCE.getType();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TYPE__VALUE = eINSTANCE.getType_Value();

  }

} //HxmlPackage
