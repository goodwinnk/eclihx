package eclihx.ui.internal.ui.editors.hx;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.editors.text.FileDocumentProvider;

public class HXDocumentProvider extends FileDocumentProvider {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.editors.text.StorageDocumentProvider#createDocument(java.lang.Object)
	 */
	@Override
	protected IDocument createDocument(Object element) throws CoreException {
		
		IDocument document = super.createDocument(element);
		if (document != null) {
			IDocumentPartitioner partitioner =
				new FastPartitioner(
					new HXPartitionScanner(),
					new String[] {
						IHXPartitions.HX_SINGLE_LINE_COMMENT,
						IHXPartitions.HX_MULTI_LINE_COMMENT,
						IHXPartitions.HX_STRING,
						IHXPartitions.HX_REGEXPR,
						IHXPartitions.HX_PREPROCESSOR });
			partitioner.connect(document);
			document.setDocumentPartitioner(partitioner);
		}
		
		return document;
	}
}