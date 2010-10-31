/**
 * <copyright>
 * </copyright>
 *
 */
package eclihx.hxml.hxml.util;

import eclihx.hxml.hxml.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see eclihx.hxml.hxml.HxmlPackage
 * @generated
 */
public class HxmlAdapterFactory extends AdapterFactoryImpl
{
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static HxmlPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public HxmlAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = HxmlPackage.eINSTANCE;
    }
  }

  /**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
   * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
   * <!-- end-user-doc -->
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
  @Override
  public boolean isFactoryForType(Object object)
  {
    if (object == modelPackage)
    {
      return true;
    }
    if (object instanceof EObject)
    {
      return ((EObject)object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected HxmlSwitch<Adapter> modelSwitch =
    new HxmlSwitch<Adapter>()
    {
      @Override
      public Adapter caseModel(Model object)
      {
        return createModelAdapter();
      }
      @Override
      public Adapter caseClasspath(Classpath object)
      {
        return createClasspathAdapter();
      }
      @Override
      public Adapter caseJavascript(Javascript object)
      {
        return createJavascriptAdapter();
      }
      @Override
      public Adapter caseSwf(Swf object)
      {
        return createSwfAdapter();
      }
      @Override
      public Adapter caseSwf9(Swf9 object)
      {
        return createSwf9Adapter();
      }
      @Override
      public Adapter caseNeko(Neko object)
      {
        return createNekoAdapter();
      }
      @Override
      public Adapter casePhp(Php object)
      {
        return createPhpAdapter();
      }
      @Override
      public Adapter caseCpp(Cpp object)
      {
        return createCppAdapter();
      }
      @Override
      public Adapter caseXml(Xml object)
      {
        return createXmlAdapter();
      }
      @Override
      public Adapter caseMain(Main object)
      {
        return createMainAdapter();
      }
      @Override
      public Adapter caseLib(Lib object)
      {
        return createLibAdapter();
      }
      @Override
      public Adapter caseFlag(Flag object)
      {
        return createFlagAdapter();
      }
      @Override
      public Adapter caseVerbose(Verbose object)
      {
        return createVerboseAdapter();
      }
      @Override
      public Adapter caseDebug(Debug object)
      {
        return createDebugAdapter();
      }
      @Override
      public Adapter caseType(Type object)
      {
        return createTypeAdapter();
      }
      @Override
      public Adapter defaultCase(EObject object)
      {
        return createEObjectAdapter();
      }
    };

  /**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
  @Override
  public Adapter createAdapter(Notifier target)
  {
    return modelSwitch.doSwitch((EObject)target);
  }


  /**
   * Creates a new adapter for an object of class '{@link eclihx.hxml.hxml.Model <em>Model</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see eclihx.hxml.hxml.Model
   * @generated
   */
  public Adapter createModelAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link eclihx.hxml.hxml.Classpath <em>Classpath</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see eclihx.hxml.hxml.Classpath
   * @generated
   */
  public Adapter createClasspathAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link eclihx.hxml.hxml.Javascript <em>Javascript</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see eclihx.hxml.hxml.Javascript
   * @generated
   */
  public Adapter createJavascriptAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link eclihx.hxml.hxml.Swf <em>Swf</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see eclihx.hxml.hxml.Swf
   * @generated
   */
  public Adapter createSwfAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link eclihx.hxml.hxml.Swf9 <em>Swf9</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see eclihx.hxml.hxml.Swf9
   * @generated
   */
  public Adapter createSwf9Adapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link eclihx.hxml.hxml.Neko <em>Neko</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see eclihx.hxml.hxml.Neko
   * @generated
   */
  public Adapter createNekoAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link eclihx.hxml.hxml.Php <em>Php</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see eclihx.hxml.hxml.Php
   * @generated
   */
  public Adapter createPhpAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link eclihx.hxml.hxml.Cpp <em>Cpp</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see eclihx.hxml.hxml.Cpp
   * @generated
   */
  public Adapter createCppAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link eclihx.hxml.hxml.Xml <em>Xml</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see eclihx.hxml.hxml.Xml
   * @generated
   */
  public Adapter createXmlAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link eclihx.hxml.hxml.Main <em>Main</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see eclihx.hxml.hxml.Main
   * @generated
   */
  public Adapter createMainAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link eclihx.hxml.hxml.Lib <em>Lib</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see eclihx.hxml.hxml.Lib
   * @generated
   */
  public Adapter createLibAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link eclihx.hxml.hxml.Flag <em>Flag</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see eclihx.hxml.hxml.Flag
   * @generated
   */
  public Adapter createFlagAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link eclihx.hxml.hxml.Verbose <em>Verbose</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see eclihx.hxml.hxml.Verbose
   * @generated
   */
  public Adapter createVerboseAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link eclihx.hxml.hxml.Debug <em>Debug</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see eclihx.hxml.hxml.Debug
   * @generated
   */
  public Adapter createDebugAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link eclihx.hxml.hxml.Type <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see eclihx.hxml.hxml.Type
   * @generated
   */
  public Adapter createTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
   * This default implementation returns null.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @generated
   */
  public Adapter createEObjectAdapter()
  {
    return null;
  }

} //HxmlAdapterFactory
