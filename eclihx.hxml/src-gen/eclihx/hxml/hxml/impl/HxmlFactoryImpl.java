/**
 * <copyright>
 * </copyright>
 *
 */
package eclihx.hxml.hxml.impl;

import eclihx.hxml.hxml.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class HxmlFactoryImpl extends EFactoryImpl implements HxmlFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static HxmlFactory init()
  {
    try
    {
      HxmlFactory theHxmlFactory = (HxmlFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.xtext.org/example/HXml"); 
      if (theHxmlFactory != null)
      {
        return theHxmlFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new HxmlFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public HxmlFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case HxmlPackage.MODEL: return createModel();
      case HxmlPackage.CLASSPATH: return createClasspath();
      case HxmlPackage.JAVASCRIPT: return createJavascript();
      case HxmlPackage.SWF: return createSwf();
      case HxmlPackage.SWF9: return createSwf9();
      case HxmlPackage.NEKO: return createNeko();
      case HxmlPackage.PHP: return createPhp();
      case HxmlPackage.CPP: return createCpp();
      case HxmlPackage.XML: return createXml();
      case HxmlPackage.MAIN: return createMain();
      case HxmlPackage.LIB: return createLib();
      case HxmlPackage.FLAG: return createFlag();
      case HxmlPackage.VERBOSE: return createVerbose();
      case HxmlPackage.DEBUG: return createDebug();
      case HxmlPackage.TYPE: return createType();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Model createModel()
  {
    ModelImpl model = new ModelImpl();
    return model;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Classpath createClasspath()
  {
    ClasspathImpl classpath = new ClasspathImpl();
    return classpath;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Javascript createJavascript()
  {
    JavascriptImpl javascript = new JavascriptImpl();
    return javascript;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Swf createSwf()
  {
    SwfImpl swf = new SwfImpl();
    return swf;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Swf9 createSwf9()
  {
    Swf9Impl swf9 = new Swf9Impl();
    return swf9;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Neko createNeko()
  {
    NekoImpl neko = new NekoImpl();
    return neko;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Php createPhp()
  {
    PhpImpl php = new PhpImpl();
    return php;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Cpp createCpp()
  {
    CppImpl cpp = new CppImpl();
    return cpp;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Xml createXml()
  {
    XmlImpl xml = new XmlImpl();
    return xml;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Main createMain()
  {
    MainImpl main = new MainImpl();
    return main;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Lib createLib()
  {
    LibImpl lib = new LibImpl();
    return lib;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Flag createFlag()
  {
    FlagImpl flag = new FlagImpl();
    return flag;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Verbose createVerbose()
  {
    VerboseImpl verbose = new VerboseImpl();
    return verbose;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Debug createDebug()
  {
    DebugImpl debug = new DebugImpl();
    return debug;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Type createType()
  {
    TypeImpl type = new TypeImpl();
    return type;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public HxmlPackage getHxmlPackage()
  {
    return (HxmlPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static HxmlPackage getPackage()
  {
    return HxmlPackage.eINSTANCE;
  }

} //HxmlFactoryImpl
